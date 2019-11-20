package com.project.miu.controller;

import com.project.miu.bean.model.Coupons;
import com.project.miu.bean.model.Great;
import com.project.miu.commons.util.Result;
import com.project.miu.commons.util.ResultUtil;
import com.project.miu.service.GreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GreatController {
    @Autowired
    private GreatService greatService;

    /**
     * 增加收藏  //todo 未测试
     * @param user_uuid
     * @param coupons_uuid
     * @return
     */
    @RequestMapping(value = "/addGreat",method = {RequestMethod.POST})
    public Result addGreat(Long user_uuid,Long coupons_uuid){
        if(user_uuid == null || coupons_uuid == null){
            return ResultUtil.error();
        }
        int res = greatService.addGreat(user_uuid,coupons_uuid);
        return ResultUtil.success(res);
    }

    /**
     * 取消收藏 //todo 未测试
     * @param user_uuid
     * @param coupons_uuid
     * @return
     */
    @RequestMapping(value = "/decGreat",method = {RequestMethod.POST})
    public Result decGreat(Long user_uuid,Long coupons_uuid){
        if(user_uuid == null || coupons_uuid == null){
            return ResultUtil.error();
        }
        int res = greatService.decGreat(user_uuid,coupons_uuid);
        if(res == 0){
            return ResultUtil.error("参数错误！");
        }
        return ResultUtil.success(res);
    }

    /**
     * 查看收藏信息
     * @param userUuid
     * @return
     */
    @RequestMapping(value = "/getGreat",method = {RequestMethod.POST})
    public Result getGreat(long userUuid){
        if(userUuid == 0l){
            return ResultUtil.error("参数错误！");
        }
        List<Coupons> res = greatService.getGreat(userUuid);
        return ResultUtil.success(res);
    }
}
