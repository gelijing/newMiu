package com.project.miu.controller;

import com.project.miu.bean.dto.SessionUserDTO;
import com.project.miu.commons.util.ResultUtil;
import com.project.miu.commons.util.SecurityUtil;
import com.project.miu.commons.util.TokenUtil;
import com.project.miu.commons.myEnum.ResultEnum;
import com.project.miu.commons.util.Result;
import com.project.miu.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static com.project.miu.commons.constants.Constants.SESSION_TOKEN_KEY;
import static com.project.miu.commons.constants.Constants.SESSION_USERNAME_KEY;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录帐号
     * @param userName 用户名
     * @param password 密码 //todo 增加用户id
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = {RequestMethod.GET})
    public Result login(String userName, String password, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if(StringUtils.isEmpty(userName)|| StringUtils.isEmpty(password)){
            return ResultUtil.error(ResultEnum.USER_NOT_EXIST.getCode(),ResultEnum.USER_NOT_EXIST.getMsg());
        }
        Map<String, Object> res = loginService.login(userName,password,request);
        if(res == null){
            return ResultUtil.error(ResultEnum.USER_NOT_EXIST.getCode(),ResultEnum.USER_NOT_EXIST.getMsg());
        }
        return ResultUtil.success(res);
    }

    /**
     * 注册
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    public Result register(String userName,String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            return ResultUtil.error(ResultEnum.USER_NOT_EXIST.getCode(),ResultEnum.USER_NOT_EXIST.getMsg());
        }
        int res = loginService.register(userName, password);
        if(res == 0){
            return ResultUtil.error(ResultEnum.USER_NOT_EXIST.getCode(),ResultEnum.USER_NOT_EXIST.getMsg());
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/exit",method = {RequestMethod.POST})
    //todo token中应该包含userName
    public Result exit(String userName, HttpServletRequest request){
        if(StringUtils.isEmpty(userName)){
            return ResultUtil.error(ResultEnum.ERROR.getCode(),ResultEnum.ERROR.getMsg());
        }
        HttpSession session = request.getSession();
        String sessionToken = (String) session.getAttribute(SESSION_TOKEN_KEY);
        String name = (String) session.getAttribute(SESSION_USERNAME_KEY);
        if (sessionToken != null && name != null && name.equals(userName)) {
            session.removeAttribute(SESSION_USERNAME_KEY);
            session.removeAttribute(SESSION_TOKEN_KEY);
        }else {
            return ResultUtil.error(ResultEnum.ERROR.getCode(),ResultEnum.ERROR.getMsg());
        }
        return ResultUtil.success();
    }

}
