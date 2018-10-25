package com.baizhi.controller;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jia on 2018/10/23.
 */
@Controller
public class MenuController {
    @Autowired
    private MenuService service;
    @RequestMapping("/getAll")
    public @ResponseBody List<Menu> getAll(){
        List<Menu>list=service.getMenuByAll();
        System.out.println(list);
        return list;
    }
}
