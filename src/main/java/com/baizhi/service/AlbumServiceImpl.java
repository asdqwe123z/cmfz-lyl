package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jia on 2018/10/25.
 */
@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao dao;
    @Autowired
    HttpServletRequest request;
    @Override
    public Map selectAlbumAll(int page, int rows) {
        Map map=new HashMap();
        int start=(page-1)*rows;
        int end=page*rows;
        List<Album>list=dao.selectAlbumAll(start,end);
        int count=dao.selectAlbumCount();
        map.put("rows",list);
        map.put("total",count);
        return map;
    }

    @Override
    public String selectAlbumId(int id) {
        return dao.selectAlbumId(id);
    }

    @Override
    public void addAlbumAll(Album album,MultipartFile tupian) throws IOException {
        String path=request.getSession().getServletContext().getRealPath("/");
        File file=new File(path+"/img");
        //获取文件名
        String filename=tupian.getOriginalFilename();
        //获取UUID字符串
        String s= UUID.randomUUID().toString();
        //获取文件的扩展名
        String s1= FilenameUtils.getExtension(filename);
        //进行拼接，获取新文件名
        String ssd=s+"."+s1;
        //将新名字存入对象中,
        String url="\\img\\"+ssd;
        album.setCoverImg(url);
        //进行文件上传
        tupian.transferTo(new File(file,ssd));
        System.out.println(album);
        dao.addAlbumAll(album);
    }
}
