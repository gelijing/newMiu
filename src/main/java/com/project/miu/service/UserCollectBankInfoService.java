package com.project.miu.service;

import com.project.miu.bean.model.UserCollectBank;
import com.project.miu.bean.model.UserCollectCoupons;
import com.project.miu.dao.UserCollectBankDao;
import com.project.miu.dao.UserCollectCouponsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: gelj
 * @Date: 2020/5/1 4:10 下午
 */
@Service
public class UserCollectBankInfoService {
    @Autowired
    private UserCollectBankDao userCollectBankDao;

    @Autowired
    private UserCollectCouponsDao userCollectCouponsDao;

    /**
     * 收藏银行或优惠活动
     * @param uuid
     * @param flag 1: 银行 2 优惠券
     */
    public void collectInfo(String userUuid,String uuid,Integer flag){
        if (flag ==1 ){
            UserCollectBank userCollectBank = new UserCollectBank();
            userCollectBank.setUserUuid(userUuid);
            userCollectBank.setBankUuid(uuid);
            userCollectBankDao.save(userCollectBank);
        }else if (flag ==2){
            UserCollectCoupons userCollectCoupons = new UserCollectCoupons();
            userCollectCoupons.setUserUuid(userUuid);
            userCollectCoupons.setCouponsUuid(uuid);
            userCollectCouponsDao.save(userCollectCoupons);
        }
    }


}
