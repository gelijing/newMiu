package com.project.miu.service;

import com.project.miu.bean.bo.CouponsBO;
import com.project.miu.bean.model.Coupons;
import com.project.miu.bean.vo.CouponsListVO;
import com.project.miu.commons.util.DateUtils;
import com.project.miu.dao.CouponsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CouponsListService {

    @Autowired
    private CouponsDao couponsDao;

    /**
     * 获取优惠券详情信息
     * @param categoryUuid
     * @param pageNum
     * @param pageSize
     * @return
     */
    public CouponsListVO getCouponsList(Long categoryUuid, Integer pageNum, Integer pageSize) {
        /*ArrayList<String> list = new ArrayList<>();
        Sort sort = new Sort(Sort.Direction.DESC,list);*/
        //todo 排序问题
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Page<Coupons> couponsPage = couponsDao.findByCategoryUuid(categoryUuid,pageable);
        int total = (int) couponsPage.getTotalElements();
        List<Coupons> couponsList = couponsPage.getContent();
        CouponsListVO couponsListVO = new CouponsListVO();
        couponsListVO.setTotalNum(total);
        couponsListVO.setCouponsList(couponsList);
        return couponsListVO;
    }

    public CouponsListVO getCouponsListByBankId(Long bankId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Page<Coupons> couponsPage = couponsDao.findByBankUuid(bankId,pageable);
        int total = (int) couponsPage.getTotalElements();
        List<Coupons> couponsList = couponsPage.getContent();
        CouponsListVO couponsListVO = new CouponsListVO();
        couponsListVO.setTotalNum(total);
        couponsListVO.setCouponsList(couponsList);
        return couponsListVO;
    }

    /**
     * 存优惠券信息
     * @param couponsBO
     * @return
     */
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

    /**
     * 获取过期优惠券信息
     * @param nowTime
     * @return
     */
    public List<Coupons> getCouponsList(String nowTime){
        Date now = DateUtils.string2Date(nowTime);
        List<Coupons> couponsList = couponsDao.findByEndTimeLessThan(now);
        return couponsList;
    }

}
