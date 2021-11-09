package com.no1.cz.controller.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.no1.cz.mapper.UserMapper;
import com.no1.cz.pojo.MyPay;
import com.no1.cz.pojo.User;
import com.no1.cz.service.AdminService;
import com.no1.cz.service.MyPayService;
import com.no1.cz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyPayService myPayService;

    @RequestMapping("/toregister")
    public String toRegister(){
        return "user/register";
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request){
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        User user=new User();
        user.setName(name).setUserName(username).setPassword(password).setSex(sex).setPhone(phone);
        userService.insertOne(user);
        return "user/login";
    }

    @RequestMapping("/tologin")
    public String tologin(){
        return "user/login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request,Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("接收到用户登录的用户名： " + username);
        log.info("接收到用户登录的密码： " + password);
        try{
            User user = userService.findByUser(username,password);
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("username",username);
            model.addAttribute("user", user);

            List<MyPay> myPay = myPayService.findByName(username);
            model.addAttribute("myPay",myPay);
            return "redirect:/goods/showPage/1";
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errMsg",e.getMessage());
            return "user/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        return "goods/vi";
    }



    @RequestMapping("/showPage/{pageNum}")
    public String showPage(@PathVariable("pageNum") Integer pageNum,Model model){
        if(pageNum < 1)
            pageNum = 1;
        PageHelper.startPage(pageNum, 8);
        List<User> userList = userService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        model.addAttribute("pageInfo", pageInfo);
        return "user/show";
    }

 /*   @RequestMapping("/showOne/{id}")
    public String showOne(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/showOne";
    }*/
    @RequestMapping("/touUpdate")
    public String touUpdate(Model model,@RequestParam("id") long id){
        log.info("跳转到user/update.html页面...");
        User user = new User();
        user.setId(id).setPhone("").setName("").setPassword("").setSex("");
        model.addAttribute("user", user);
        return "user/uUpdate";
    }
    @RequestMapping("/uUpdate")
    public String uUpdate(HttpServletRequest request, Model model){
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String sex = request.getParameter("userName");
        User user = new User();
        user.setId(id).setName(name).setPassword(password).setSex(sex);
        userService.updateUserById(user);
//        model.addAttribute("user", user);
        return "redirect:/user/tologin";
    }

    @RequestMapping("/toupdate")
    public String toupdate(Model model, @RequestParam("id")long id){
        log.info("跳转到user/update.html页面...");
        User user = new User();
        user.setId(id).setPhone("").setName("").setPassword("").setSex("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, Model model){
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        User user = new User();
        user.setId(id).setName(name).setPhone(phone).setPassword(password).setSex(sex);
        userService.updateUserById(user);
//        model.addAttribute("user", user);
        return "redirect:/user/show";
    }
    //根据用户uid删去用户
    @RequestMapping("/deleteUser")
    public ModelAndView deleteUser(Integer id){
        int num=userService.deleteUserById(id);
        if(num>0){
            return new ModelAndView("forward:/user/show");
        }
        return null;
    }
    @RequestMapping("/toaddUser")
    public String toaddUser(){
        return "user/addUser";
    }

    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request){
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        User user=new User();
        user.setName(name).setUserName(username).setPassword(password).setSex(sex).setPhone(phone);
        userService.insertOne(user);
        return "forward:/user/show";
    }
}


