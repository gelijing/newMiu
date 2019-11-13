package com.project.miu.service;

import com.project.miu.bean.UserInfo;
import com.project.miu.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public int login(String userName,String password){
        UserInfo userInfo = loginDao.findByUserName(userName);
        if(userInfo != null && userInfo.getPassword().equals(password)){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 注册
     * @param userName
     * @param password
     * @return
     */
    public int register(String userName, String password) {
        UserInfo user = loginDao.findByUserName(userName);
        if(user != null){
            return 0;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(password);
        userInfo.setStatus(1);
        UserInfo save = loginDao.save(userInfo);
        if (save == null){
            return 0;
        }else{
            return 1;
        }

    }
}
