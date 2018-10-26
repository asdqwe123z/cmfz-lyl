package com.baizhi.service;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
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
        String path=request.getSession().getServletContext().getRealPath("/");
        File file=new File(path+"/img");
        //获取文件名
        String filename=tupian.getOriginalFilename();
        //获取文件的扩展名
        String s1= FilenameUtils.getExtension(filename);
        //进行拼接，获取新文件名
        String ssd=new Date().getTime()+"."+s1;
        //将新名字存入对象中,
        chapter.setUrl(ssd);
        //进行文件上传
        tupian.transferTo(new File(file,ssd));
        System.out.println(chapter);
        dao.addCapter(chapter);
    }
}
