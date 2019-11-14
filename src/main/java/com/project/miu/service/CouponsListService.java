package com.project.miu.service;

import com.project.miu.bean.bo.CouponsBO;
import com.project.miu.bean.model.Coupons;
import com.project.miu.bean.model.UserInfo;
import com.project.miu.commons.util.DateUtils;
import com.project.miu.dao.CouponsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CouponsListService {

    @Autowired
    private CouponsDao couponsDao;

    public List<Coupons> getCouponsList(Integer categoryUuid) {
        List<Coupons> couponsList = couponsDao.findByCategoryUuid(categoryUuid);
        return couponsList;
    }

    public int putCouponsList(CouponsBO couponsBO) {
        Coupons coup = couponsDao.findByUuid(couponsBO.getUuid());
        if(coup == null){
            Coupons coupons = new Coupons();
            coupons.setUuid(couponsBO.getUuid());
            coupons.setTitle(couponsBO.getTitle());
            coupons.setMoney(couponsBO.getMoney());//todo 金额
            coupons.setAddress(couponsBO.getAddress());
            coupons.setDescription(couponsBO.getDesc());
            coupons.setCategoryUuid(couponsBO.getCategoryUuid());
            String startTime = couponsBO.getStartTime();
            coupons.setStartTime(StringUtils.isEmpty(startTime) ? null : DateUtils.str2dateTime(startTime));
            String endTime = couponsBO.getEndTime();
            coupons.setEndTime(StringUtils.isEmpty(endTime) ? null : DateUtils.str2dateTime(endTime));
            String createTime = couponsBO.getCreateTime();
            coupons.setCreateTime(StringUtils.isEmpty(createTime)? new Timestamp(new Date().getTime()) : DateUtils.str2dateTime(createTime));
            String updateTime = couponsBO.getUpdateTime();
            coupons.setUpdateTime(StringUtils.isEmpty(updateTime) ? new Timestamp(new Date().getTime()) : DateUtils.str2dateTime(updateTime));
            coupons.setDeleteData(1);
            Coupons save = couponsDao.save(coupons);
            if(save != null){
                return 1;
            }
        }
        return 0;
    }
}