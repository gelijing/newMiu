package com.project.miu.controller;

import com.project.miu.commons.util.Result;
import com.project.miu.commons.util.ResultUtil;
import com.project.miu.service.PointExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: gelj
 * @Date: 2020/5/22 2:16 下午
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private PointExchangeService pointExchangeService;

    @RequestMapping(value = "/buyGoods",method = RequestMethod.POST)
    public Result buyGoods(@RequestParam("userUuid")String userUuid,
                           @RequestParam("goodsUuid")String goodsUuid) throws Exception {
        Integer res = pointExchangeService.pointExchange(userUuid, goodsUuid);
        /*if (res == 1){
            return ResultUtil.success();
        }else {
            return ResultUtil.error(501,"积分余额不足！");
        }*/
        Map<String,Integer> resMap = new HashMap<>();
        resMap.put("result",res);
        return ResultUtil.success(resMap);
    }

}
