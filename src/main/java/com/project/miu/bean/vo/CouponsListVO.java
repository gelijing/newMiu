package com.project.miu.bean.vo;

import com.project.miu.bean.model.Coupons;

import java.util.List;

public class CouponsListVO {
    private List<Coupons> couponsList;
    private Integer totalNum;

    public List<Coupons> getCouponsList() {
        return couponsList;
    }

    public void setCouponsList(List<Coupons> couponsList) {
        this.couponsList = couponsList;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
