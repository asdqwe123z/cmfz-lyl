package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;
import java.util.Map;

/**
 * Created by jia on 2018/10/24.
 */
public interface BannerService {
    public Map getAll(int page, int rows);
    public void updataBanner(int id,int status);
    public void addBanner(Banner banner);
    public void deleteId(int id);
}
