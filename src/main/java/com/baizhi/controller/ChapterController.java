package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by jia on 2018/10/25.
 */
@Controller
public class ChapterController {
    @Autowired
    ChapterService service;
    @RequestMapping("/addChapter")
    public @ResponseBody boolean addChapter(Chapter chapter, MultipartFile tupian){

        try {
            service.addChapter(chapter,tupian);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @RequestMapping("/dowload")
    public  void dowload(String url, HttpServletRequest request, HttpServletResponse response){

        try {
            String realPath=request.getRealPath("/img");
            System.out.println(realPath);
            File file=new File(realPath+"/"+url);
            response.setHeader("content-disposition","attachment;url="+url);
            FileUtils.copyFile(file,response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
