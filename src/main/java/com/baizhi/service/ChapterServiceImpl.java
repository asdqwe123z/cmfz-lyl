package com.baizhi.service;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jia on 2018/10/25.
 */
@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao dao;
    @Autowired
    HttpServletRequest request;
    @Override
    public void addChapter(Chapter chapter, MultipartFile tupian) throws IOException {
        //获取当前路径
        String path=request.getSession().getServletContext().getRealPath("/");

        //获取文件要储存的位置
        File file=new File(path+"/img");

        //获取文件全名(扩展名)
        String filename=tupian.getOriginalFilename();

        //获取文件的名字
        String name=filename.substring(0,filename.indexOf("."));
        //获取文件的扩展名
        String s1= FilenameUtils.getExtension(filename);

        //进行拼接，获取新文件名
        String newName=new Date().getTime()+"."+s1;

        //获取文件的大小
        long dx = tupian.getSize();
        double d = dx/1024/1024;
        String size=d+"MB";
        //将新文件名存入对象中,


        //进行文件上传
        tupian.transferTo(new File(file,newName));
        File source = new File(file+"/"+newName);
        Encoder encoder = new Encoder();
        String ss=null;
        try {
            MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration();
            int second = (int) (ls/1000/60);
             ss=second+"分钟";
            System.out.println(ss);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将章节名,文件地址,文件大小,文件时长存入对象中
        chapter.setUrl(newName);
        chapter.setName(name);
        chapter.setSize(size);
        chapter.setDuration(ss);
        System.out.println(chapter);
        dao.addCapter(chapter);
    }
    //mvn install:install-file -Dfile=D:\下载\jave-1.0.2.jar -DgroupId=jave -DartifactId=jave -Dversion=1.0.2 -Dpackaging=jar -DgeneratePom=true
}
