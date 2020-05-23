package com.project.miu.service;

import com.project.miu.bean.model.UserBalance;
import com.project.miu.dao.UserBalanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @Author: gelj
 * @Date: 2020/5/22 2:18 下午
 */

@Service
public class UserBalanceService {

    @Autowired
    private UserBalanceDao userBalanceDao;

    //查询用户余额
    public UserBalance findUserBalance(String userUuid){
        UserBalance userBalance = userBalanceDao.findByUserUuidAndDeleteData(userUuid,1);
        Assert.isTrue(userBalance!=null,"账户异常，请稍后再试");
        return userBalance;
    }
}
