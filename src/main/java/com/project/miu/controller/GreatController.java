package com.project.miu.controller;

import com.project.miu.bean.model.Coupons;
import com.project.miu.commons.util.Result;
import com.project.miu.commons.util.ResultUtil;
import com.project.miu.service.GreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collect")
public class GreatController {
    @Autowired
    private GreatService greatService;

    /**
     * 增加收藏  //todo 未测试
     * @param user_uuid
     * @param coupons_uuid
     * @return
     */
    @RequestMapping(value = "/addCollect",method = {RequestMethod.POST})
    public Result addGreat(String user_uuid,String coupons_uuid){
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
    @RequestMapping(value = "/delCollect",method = {RequestMethod.POST})
    public Result decGreat(String user_uuid,String coupons_uuid){
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
    @RequestMapping(value = "/viewCollect",method = {RequestMethod.POST})
    public Result getGreat(String userUuid){
        if(StringUtils.isEmpty(userUuid)){
            return ResultUtil.error("参数错误！");
        }
        List<Coupons> res = greatService.getGreat(userUuid);
        return ResultUtil.success(res);
    }
}
