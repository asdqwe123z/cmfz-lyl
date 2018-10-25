package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by jia on 2018/10/24.
 */
@Controller
public class BannerController {
    @Autowired
    private BannerService service;
    @RequestMapping("/getBannerAll")
    public @ResponseBody Map getBannerAll(int page,int rows){
        System.out.println(page+"=="+rows);
        System.out.println(service.getAll(page,rows));
        return service.getAll(page,rows);
    }
    @RequestMapping("/updateBanner")
    public void updateBanner(int id,int status){
        System.out.println(id+"--"+status);
        System.out.println("2222222222");
        service.updataBanner(id,status);
    }
    @RequestMapping("/addBanner")
    public @ResponseBody boolean addBanner(Banner banner, MultipartFile tupian) {
        System.out.println("111111111");


        try {
            String filename = tupian.getOriginalFilename();
            String filenames = new Date().getTime() + "-" + filename;
            banner.setUrl("\\img\\"+filenames);
            service.addBanner(banner);
            File file = new File("E:/JavaEE/source/cmfz-lyl/src/main/webapp/img/" + filenames);
            tupian.transferTo(file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }
    @RequestMapping("/deleteId")
    public @ResponseBody boolean deleteId(int id){
        try {
            service.deleteId(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

}
