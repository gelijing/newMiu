package com.project.miu.config.intercepors;


import com.alibaba.fastjson.JSON;
import com.project.miu.bean.dto.SessionUserDTO;
import com.project.miu.commons.myEnum.ResultEnum;
import com.project.miu.commons.util.IdGenerateUtil;
import com.project.miu.commons.util.SecurityUtil;
import com.project.miu.commons.util.WebUtils;
import com.project.miu.commons.util.Result;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.project.miu.commons.constants.Constants.SESSION_USERNAME_KEY;
import static com.project.miu.commons.constants.Constants.SESSION_USERID_KEY;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
        HttpSession session = request.getSession();
        //这里的User是登陆时放入session的
        String userName = (String) session.getAttribute(SESSION_USERNAME_KEY);
        String userId = (String) session.getAttribute(SESSION_USERID_KEY);
        //如果session中没有user，表示没登陆
        if (StringUtils.isEmpty(userName)){
            //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
            Result result = new Result();
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg("请先登录！");
            WebUtils.writeToPage(JSON.toJSONString(result), response);
            return false;
        }else {
            SessionUserDTO userInfo = new SessionUserDTO();
            userInfo.setMemberId(userId);
            userInfo.setLoginName(userName);
            SecurityUtil.setUser(userInfo);
            return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}