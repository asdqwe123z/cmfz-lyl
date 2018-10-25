package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by jia on 2018/10/23.
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService service;

    @RequestMapping("/login")
    public String login(String enCode,Admin admin, HttpServletRequest request){
        HttpSession session=request.getSession();
        Admin admin1=service.login(admin);
        String code= (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);

        if(admin1!=null && enCode.equals(code)){

            return "redirect:/main/main.jsp";
        }else {
                return "redirect:/login.jsp";
        }

    }
}
