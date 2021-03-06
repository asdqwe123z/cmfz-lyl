package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
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
 * Created by jia on 2018/10/24.
 */
@Service
public class BannerServiceImpl implements BannerService{
    @Autowired
    private BannerDao dao;
    @Autowired
    HttpServletRequest request;
    @Override
    public Map getAll(int page, int rows) {
        Map map=new HashMap();
        int start=(page-1)*rows;
        int end=page*rows;
        List<Banner>list=dao.getBannerByAll(start,end);
        int count=dao.getCount();
        map.put("rows",list);
        map.put("total",count);
        return map;
    }

    @Override
    public void updataBanner(int id, int status) {
        dao.updateBannerById(id,status);
    }

    @Override
    public void addBanner(Banner banner,MultipartFile tupian) throws IOException {
        //获取要储存的文件路径
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
        banner.setUrl(url);
        //进行文件上传
        tupian.transferTo(new File(file,ssd));
        dao.addBanner(banner);
    }

    @Override
    public void deleteId(int id) {
        dao.deleteId(id);
    }
}
