package com.no1.cz.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.no1.cz.config.AlipayConfig;
import com.no1.cz.pojo.Goods;
import com.no1.cz.pojo.MyPay;
import com.no1.cz.pojo.User;
import com.no1.cz.service.GoodsService;
import com.no1.cz.service.MyPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/myPay")
public class MyPayController {

    @Autowired
    private MyPayService myPayService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/succ")
    public String succ() {
        log.info("支付成功，跳转到成功显示页面...");
        AlipayConfig.logResult("支付成功，跳转到成功显示页面...");
        return "goods/vi";
    }

    @RequestMapping("/toNotify")
    public String toNotify() {
        return "pay/notify";
    }



    @RequestMapping("/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);
        //获取用户名
        String username = (String)request.getSession().getAttribute("username");
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("tradeNo").getBytes("ISO-8859-1"),"UTF-8");
        //付款金额，必填
        String total_amount = new String(request.getParameter("amount").getBytes("ISO-8859-1"),"UTF-8");
        //订单名称，必填
        String subject = new String(request.getParameter("name").getBytes("UTF-8"),"UTF-8");
        //商品描述，可空
        String body = new String(request.getParameter("desc").getBytes("UTF-8"),"UTF-8");
        String goodsId = new String(request.getParameter("goodsId").getBytes("UTF-8"),"UTF-8");

        //订单添加到数据库

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String format = dateFormat.format(calendar.getTime());
        MyPay pay=new MyPay();
        pay.setTradeNo(out_trade_no).setAmount(total_amount).setPayName(subject).setPayDesc(body).setUserName(username).setPayDate(format).setGid(Integer.parseInt(goodsId));
        myPayService.insertOne(pay);

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }



    @RequestMapping("/allPay/{pageNum}")
    public String showAll(@PathVariable("pageNum") Integer pageNum,Model model){
        if(pageNum <1)
            pageNum=1;
        PageHelper.startPage(pageNum,8);
        List<MyPay> payList = myPayService.findAll();
        PageInfo<MyPay> pageInfo= new PageInfo<>(payList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/allPay";
    }

    //用户查看自己订单
        @RequestMapping("/showPay/{username}")
        public String showOne(@PathVariable("username") String username, Model model){
            List<MyPay> myPayList = myPayService.findByName(username);
            model.addAttribute("myPayList", myPayList);
            return "user/showPay";
    }
    //管理员查看用户订单
    @RequestMapping("/showOne/{username}")
    public String adminShowOne(@PathVariable("username") String username, Model model){
        List<MyPay> myPayList = myPayService.findByName(username);
        model.addAttribute("myPayList", myPayList);
        return "user/showOne";
    }
    //再来一单
    @RequestMapping("/findPay/{goodsName}")
    public String findPay(@PathVariable("goodsName") String goodsName,Model model){
        List<Goods> byName = goodsService.findByName(goodsName);
        Goods goods = byName.get(0);
        Integer goodsId = goods.getGoodsId();
        model.addAttribute("id",goodsId);
        return "redirect:/goods/showOne/"+goodsId;
    }
}