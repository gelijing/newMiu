package com.project.miu.controller;

import com.project.miu.bean.model.Bank;
import com.project.miu.commons.util.Result;
import com.project.miu.commons.util.ResultUtil;
import com.project.miu.service.UserCollectBankInfoService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gelj
 * @Date: 2020/5/1 4:08 下午
 */
@RestController
@RequestMapping(value = "/collect")
public class UserCollectBankInfoController {
    @Autowired
    private UserCollectBankInfoService userCollectBankInfoService;

    @RequestMapping(value ="/collectInfo",method = {RequestMethod.POST})
    public Result collectInfo(@RequestParam("userUuid") String userUuid,
                              @RequestParam("uuid") String uuid,
                              @RequestParam("flag") Integer flag){
        Assert.notNull(userUuid, "参数错误！");
        Assert.notNull(uuid, "参数错误！");
        Assert.notNull(flag, "参数错误！");
        userCollectBankInfoService.collectInfo(userUuid,uuid,flag);
        return ResultUtil.success();
    }
}
