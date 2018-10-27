package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public @ResponseBody boolean login(String enCode, Admin admin, HttpServletRequest request){
        HttpSession session=request.getSession();
        Admin admin1=service.login(admin);
        System.out.println(admin1);
        String code= (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);

        if(admin1!=null && enCode.equals(code)){

            return true;
        }else {
            return false;
        }

    }
}
