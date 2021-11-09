package com.no1.cz.controller.admin;

import com.no1.cz.pojo.Admin;
import com.no1.cz.pojo.User;
import com.no1.cz.service.AdminService;
import com.no1.cz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    UserService userService;
    @RequestMapping("/tologin")
    public String tologin(){
        return "admin/login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("接收到管理员登录的用户名： " + username);
        log.info("接收到管理员登录的密码： " + password);
        try{
            Admin admin = adminService.findByName(username,password);
            request.getSession().setAttribute("admin",admin);
            List<User> userList = userService.findAll();
            model.addAttribute("userList",userList);
            //return "admin/manage";
            return "redirect:../user/showPage/1";
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errMsg",e.getMessage());
            return "admin/login";
        }
    }
    @RequestMapping("/toupdate")
    public String toupdate(Model model,@RequestParam("id") long id){
        log.info("跳转到admin/update.html页面...");
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "admin/update";
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, Model model){
        Admin admin = new Admin();
        adminService.updateAdminById(admin);
        return "redirect:/admin/show";
    }
}
