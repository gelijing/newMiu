package com.project.miu.controller;

import com.project.miu.bean.bo.CouponsBO;
import com.project.miu.bean.model.Coupons;
import com.project.miu.bean.vo.CouponsListVO;
import com.project.miu.commons.util.Result;
import com.project.miu.commons.util.ResultUtil;
import com.project.miu.commons.myEnum.ResultEnum;
import com.project.miu.service.CouponsListService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 优惠券列表展示
 */
@RestController
@RequestMapping(value = "/coupons", method = {RequestMethod.POST})
public class CouponsListController {

    @Autowired
    private CouponsListService couponsListService;

    /**
     * 优惠券列表
     *
     * @param categoryUuid 类目
     * @return
     */
    @RequestMapping(value = "/getCouponsList", method = {RequestMethod.POST})
    public Result getCouponsList(@RequestParam("categoryUuid") String categoryUuid,
                                 @RequestParam("pageNum") Integer pageNum,
                                 @RequestParam("pageSize") Integer pageSize) {
        if (StringUtils.isEmpty(categoryUuid)) {
            return ResultUtil.error(ResultEnum.CATEGORY_UUID_NOT_EXIST.getCode(), ResultEnum.CATEGORY_UUID_NOT_EXIST.getMsg());
        }
        if (pageNum < 0 || pageSize <= 0) {
            return ResultUtil.error(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMsg());
        }
        CouponsListVO couponsList = couponsListService.getCouponsList(categoryUuid, pageNum, pageSize);
        return ResultUtil.success(couponsList);
    }

    /**
     * 插入列表
     *
     * @param couponsBo
     * @return
     */
    @RequestMapping(value = "/putCouponsList", method = {RequestMethod.POST})
    public Result putCouponsList(CouponsBO couponsBo) throws Exception {
        if (StringUtils.isEmpty(couponsBo.getUuid()) ||
                StringUtils.isEmpty(couponsBo.getTitle()) ||
                StringUtils.isEmpty(couponsBo.getAddress()) ||
                couponsBo.getMoney() == 0L ||
                StringUtils.isEmpty(couponsBo.getCategoryUuid()) ){
            throw new Exception("参数错误！");
        }
        int res = couponsListService.putCouponsList(couponsBo);
        if (res == 0) {
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), "添加列表失败，请稍后再试！");
        }
        return ResultUtil.success();
    }

    /**
     * 推送用户收藏银行优惠信息
     *
     * @param userUuid   用户id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getCouponsListByUserId", method = {RequestMethod.POST})
    public Result getCouponsListByUserId(@RequestParam("userUuid") String userUuid,
                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        Assert.notNull(userUuid, "参数错误！");
        CouponsListVO couponsList = couponsListService.getCouponsListByUserId(userUuid, pageNum, pageSize);
        return ResultUtil.success(couponsList);
    }

    /**
     * 查看优惠信息内容
     * @param couponsUuid
     * @return
     */
    @RequestMapping(value = "/getCouponsInfo", method = {RequestMethod.POST})
    public Result getCouponsInfo(@RequestParam("couponsUuid") String couponsUuid) {

        Assert.notNull(couponsUuid, "参数错误！");
        Coupons coupons = couponsListService.getCouponsInfo(couponsUuid);
        return ResultUtil.success(coupons);
    }

    /**
     * 筛选某一银行的优惠信息
     * @param bankUuid
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getCouponsInfoByBankId", method = {RequestMethod.POST})
    public Result getCouponsInfo(@RequestParam("bankUuid") String bankUuid,
                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize ) {

        Assert.notNull(bankUuid, "参数错误！");
        CouponsListVO coupons = couponsListService.getCouponsInfoByBankId(bankUuid,pageNum,pageSize);
        return ResultUtil.success(coupons);
    }


}
