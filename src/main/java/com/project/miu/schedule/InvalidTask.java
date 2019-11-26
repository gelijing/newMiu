package com.project.miu.schedule;

import com.project.miu.bean.model.Coupons;
import com.project.miu.bean.vo.CouponsListVO;
import com.project.miu.dao.CouponsDao;
import com.project.miu.service.CouponsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class InvalidTask {
    @Autowired
    private CouponsListService couponsListService;

    @Autowired
    private CouponsDao couponsDao;

    //定时执行
    @Scheduled(cron = "0 0/15 * * * ?")
    private void configureTasks() {
        Date date = new Date();
        List<Coupons> couponsList = couponsDao.findByEndTimeLessThan(date);
        for (Coupons coupons : couponsList) {
            //将过期优惠券删除
            coupons.setDeleteData(2);
            couponsDao.save(coupons);
        }
    }
}
