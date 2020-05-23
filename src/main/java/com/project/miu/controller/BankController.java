package com.project.miu.controller;

import com.project.miu.bean.model.Bank;
import com.project.miu.commons.util.Result;
import com.project.miu.commons.util.ResultUtil;
import com.project.miu.service.BankService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gelj
 * @Date: 2020/5/22 9:50 下午
 */
@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    private BankService bankService;

    @RequestMapping(value = "/bankInfo",method = {RequestMethod.POST})
    public Result getBankInfo(@RequestParam("bankUuid") String bankUuid) {
        Assert.notNull(bankUuid, "参数错误！");
        Bank bankInfo = bankService.getBankInfo(bankUuid);
        return ResultUtil.success(bankInfo);
    }
}
