package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by jia on 2018/10/25.
 */
public interface ChapterService {
    public void addChapter(Chapter chapter, MultipartFile tupian) throws IOException;
}
