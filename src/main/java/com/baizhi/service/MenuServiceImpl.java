package com.baizhi.service;

import com.baizhi.dao.MenuDao;
import com.baizhi.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jia on 2018/10/23.
 */
@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuDao dao;
    @Override
    public List<Menu> getMenuByAll() {
        return dao.getMenuByAll();
    }
}
