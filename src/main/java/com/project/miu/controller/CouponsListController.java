package com.project.miu.controller;

import com.project.miu.bean.bo.CouponsBO;
import com.project.miu.bean.model.Coupons;
import com.project.miu.commons.util.Result;
import com.project.miu.commons.util.ResultUtil;
import com.project.miu.commons.myEnum.ResultEnum;
import com.project.miu.service.CouponsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 优惠信息列表展示
 */
@RestController
@RequestMapping(value = "/coupons",method = {RequestMethod.POST})
public class CouponsListController {

    @Autowired
    private CouponsListService couponsListService;

    /**
     * 优惠券列表
     * @param categoryUuid
     * @return
     */
    @RequestMapping(value = "/getCouponsList",method = {RequestMethod.POST})
    public Result getCouponsList(Integer categoryUuid){
        if(categoryUuid == null || categoryUuid == 0){
            return ResultUtil.error(ResultEnum.CATEGORY_UUID_NOT_EXIST.getCode(),ResultEnum.CATEGORY_UUID_NOT_EXIST.getMsg());
        }
        List<Coupons> couponsList = couponsListService.getCouponsList(categoryUuid);
        return ResultUtil.success(couponsList);
    }

    /**
     * 插入列表
     * @param couponsBo
     * @return
     */
    @RequestMapping(value = "/putCouponsList",method = {RequestMethod.POST})
    public Result putCouponsList(CouponsBO couponsBo) throws Exception {
        if(StringUtils.isEmpty(couponsBo.getUuid())||
            StringUtils.isEmpty(couponsBo.getTitle()) ||
            StringUtils.isEmpty(couponsBo.getAddress())||
                couponsBo.getMoney()== 0L ||
                StringUtils.isEmpty(couponsBo.getCategoryUuid())
            ){
            throw new Exception("参数错误！");
        }
        int res = couponsListService.putCouponsList(couponsBo);
        if (res == 0){
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(),"添加列表失败，请稍后再试！");
        }
        return ResultUtil.success();
    }
}
