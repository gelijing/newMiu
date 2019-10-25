package com.project.miu.controller;

import com.project.miu.bean.myEnum.ResultEnum;
import com.project.miu.bean.util.ResultUtil;
import com.project.miu.bean.vo.Result;
import com.project.miu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userinfo")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public Result login(String userName, String password){
        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)){
            return ResultUtil.error(ResultEnum.USER_NOT_EXIST.getCode(),ResultEnum.USER_NOT_EXIST.getMsg());
        }
        int res = loginService.login(userName,password);
        if(res == 0){
            return ResultUtil.error(ResultEnum.USER_NOT_EXIST.getCode(),ResultEnum.USER_NOT_EXIST.getMsg());
        }
        return ResultUtil.success();
    }
}
