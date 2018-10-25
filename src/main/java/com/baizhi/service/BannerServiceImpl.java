package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jia on 2018/10/24.
 */
@Service
public class BannerServiceImpl implements BannerService{
    @Autowired
    private BannerDao dao;
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
    public void addBanner(Banner banner) {
        dao.addBanner(banner);
    }

    @Override
    public void deleteId(int id) {
        dao.deleteId(id);
    }
}
