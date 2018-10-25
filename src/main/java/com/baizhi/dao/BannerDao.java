package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jia on 2018/10/24.
 */
public interface BannerDao {
    public List<Banner> getBannerByAll(@Param("start") int start,@Param("end") int end);
    public int getCount();
    public void updateBannerById(@Param("id")int id,@Param("status")int status);
    public void addBanner(Banner banner);
    public void deleteId(int id);

}
