package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jia on 2018/10/23.
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminDao dao;
    @Override
    public Admin login(Admin admin) {
        return dao.getAdminById(admin);
    }
}
