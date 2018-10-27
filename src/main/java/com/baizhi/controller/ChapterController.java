package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by jia on 2018/10/25.
 */
@Controller
public class ChapterController {
    @Autowired
    ChapterService service;
    //章节上传
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
    //文件下载
    @RequestMapping("/dowload")
    public  void dowload(String name,String url, HttpServletRequest request, HttpServletResponse response){
        //获取要下载的文件位置
        String realPath = request.getSession().getServletContext().getRealPath("/img");

        //获取文件的位置
        String filePath = realPath + "/" + url;

        //将文件存入文件中
        File audioFile=new File(filePath);
        //获取文件的扩展名
        String extension = FilenameUtils.getExtension(url);
        //将文件进行拼接获得新的文件名(带有扩展名)
        String newName = name + "." + extension;

        try {
            //进行文件传输时的乱码解决
            response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode(newName,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //定义文件的格式
        response.setContentType("audio/mpeg");
        try {
            //将文件进行输出
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(audioFile));
            //书信流
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
