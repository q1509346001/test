package com.no1.cz.controller.goods;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.no1.cz.pojo.Goods;
import com.no1.cz.service.GoodsService;
import com.no1.cz.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/showAll")
    public String showAll(Model model){
        List<Goods> goodsList = this.goodsService.findAll();
        model.addAttribute("goodsList", goodsList);
        return "goods/showAll";
    }
    @RequestMapping("/vi")
    public String vi(Model model){
        List<Goods> goodsList = this.goodsService.findAll();
        model.addAttribute("goodsList", goodsList);
        return "goods/vi";
    }
    //分页查询
    @RequestMapping("/showPage/{pageNum}")
    public String showPage(@PathVariable("pageNum") Integer pageNum, Model model){
        if(pageNum < 1)
            pageNum = 1;
        PageHelper.startPage(pageNum, StringUtil.PAGE_SIZE);
        List<Goods> goodsList = goodsService.findAll();
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        model.addAttribute("pageInfo", pageInfo);
        //热销排行
        List<Goods> hotGoods =goodsService.findByHotGoods();
        model.addAttribute("hotGoods",hotGoods);
        return "goods/show";
    }

    @RequestMapping("/showOne/{goodsId}")
    public String showOne(@PathVariable("goodsId") Integer goodsId, Model model){
        Goods goods = this.goodsService.findById(goodsId);
        model.addAttribute("goods", goods);
        return "goods/showOne";
    }
    //获取订单信息
    @RequestMapping("/pay")
    public String pay(HttpServletRequest request, Model model){
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        String tradeNo = UUID.randomUUID().toString().replace("-", "");
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        String goodsId = request.getParameter("goodsId");
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("tradeNo", tradeNo);
        model.addAttribute("name", name);
        model.addAttribute("desc",desc);
        model.addAttribute("goodsId",goodsId);
        return "goods/pay";
    }

    //上传餐品图片
    @RequestMapping("/upload")
    public String upload(@RequestParam("goodsImg") MultipartFile file, Integer goodsId){
        if(file.isEmpty())
            return "上传图片为空，请检查！";
        String filename = file.getOriginalFilename();

        String suffixName = StringUtil.getSuffixName(filename);
        String filePath = "D://2345Downloads/myProject/src/main/resources/static/upImg/";
        String newFilename = StringUtil.getRandomFileName();
        String updatename = newFilename + suffixName;
        String fullFilename = filePath + newFilename + suffixName;
        log.info("要上传的图片会被存储到：" + fullFilename);
        File uploadFile = new File(fullFilename);
        if(!uploadFile.getParentFile().exists())
            uploadFile.getParentFile().mkdirs();
        try {
            file.transferTo(uploadFile);
            Goods goods = new Goods();
            goods.setGoodsId(goodsId);
            goods.setGoodsImg(updatename);
            goodsService.updateGoodsByImg(goods);
            return "redirect:../goods/showAll";
        } catch (IOException e) {
            e.printStackTrace();
            log.info("文件上传失败，请检查代码!");
            return "文件上传失败！";
        }
    }

    //修改餐品名称和价格
    @RequestMapping("/toupdateGoods")
    @Transactional
    public  String toupdateGoods(Integer goodsId,Model model){
        log.info("跳转到goods/updateGoods.html页面...");
        Goods goods = goodsService.findById(goodsId);
        model.addAttribute("goods",goods);
        return "goods/updateGoods";
    }

    @RequestMapping("/updateGoods")
    @Transactional
    public  String updateGoods(HttpServletRequest request,Model model){
        Goods goods = new Goods();
        int id = Integer.parseInt(request.getParameter("goodsId"));
        String goodsName = request.getParameter("goodsName");
        Double goodsPrice = Double.parseDouble(request.getParameter("goodsPrice"));
        goods.setGoodsName(goodsName).setGoodsPrice(goodsPrice).setGoodsId(id);
        goodsService.updateGoodsById(goods);
        return "redirect:../goods/showAll";
    }

    //添加餐品
    @RequestMapping("/toaddGoods")
    public String toaddGoods(){
        return "goods/addGoods";
    }


    @RequestMapping("/addGoods")
    public String addGoods(Goods goods,Model model){

        goodsService.add(goods);
        List<Goods> goodsList = this.goodsService.findAll();
        model.addAttribute("goodsList", goodsList);
        return "goods/showAll";
    }

    @RequestMapping("/emptyGoods")
    public  String emptyGoods(Integer goodsId , Model model){
        Goods goods = goodsService.findById(goodsId);
        goods.setGoodsNum(0);
        goodsService.updateGoodsById(goods);

        List<Goods> goodsList = this.goodsService.findAll();
        model.addAttribute("goodsList", goodsList);

        return "goods/showAll";
    }

    @RequestMapping("/recoverGoods")
    public  String recoverGoods(Integer goodsId , Model model){
        Goods goods = goodsService.findById(goodsId);
        goods.setGoodsNum(1);
        goodsService.updateGoodsById(goods);

        List<Goods> goodsList = this.goodsService.findAll();
        model.addAttribute("goodsList", goodsList);

        return "goods/showAll";
    }
}
