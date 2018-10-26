package com.baizhi.service;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by jia on 2018/10/25.
 */
public interface AlbumService {
    public Map selectAlbumAll(int page, int rows);
    public String selectAlbumId(int id);
    public void addAlbumAll(Album album,MultipartFile tupian) throws IOException;
}
