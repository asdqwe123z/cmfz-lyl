package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by jia on 2018/10/25.
 */
@Controller
public class AlbumController {
    @Autowired
    private AlbumService service;
    @RequestMapping("/selectAlbumAll")
    public @ResponseBody Map selectAlbumAll(int page,int rows){
        return service.selectAlbumAll(page,rows);
    }
    @RequestMapping("/selectAlbumId")
    public @ResponseBody String selectAlbumId(int id){
        return service.selectAlbumId(id);
    }
    @RequestMapping("/addAlbumAll")
    public @ResponseBody boolean addAlbumAll(Album album, MultipartFile tupian){

        try {
            service.addAlbumAll(album,tupian);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
