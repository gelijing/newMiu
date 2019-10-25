package com.project.miu.service;

import com.project.miu.bean.UserInfo;
import com.project.miu.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;

    public int login(String userName,String password){
        List<UserInfo> all = loginDao.findAll();
        if(!CollectionUtils.isEmpty(all)){
            return 1;
        }else{
            return 0;
        }
    }
}
