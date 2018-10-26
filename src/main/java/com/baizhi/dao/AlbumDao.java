package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jia on 2018/10/25.
 */
public interface AlbumDao {
    public List<Album> selectAlbumAll(@Param("start") int start, @Param("end")int end);
    public int selectAlbumCount();
    public String selectAlbumId(int id);
    public void addAlbumAll(Album album);
}
