package com.project.miu.service;

import com.project.miu.bean.model.UserInfo;
import com.project.miu.commons.util.IdGenerateUtil;
import com.project.miu.commons.util.TokenUtil;
import com.project.miu.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static com.project.miu.commons.constants.Constants.*;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public Map<String, Object> login(String userName, String password, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String newPassword = TokenUtil.EncoderByMd5(password);
        UserInfo userInfo = loginDao.findByUserName(userName);
        if(userInfo != null && userInfo.getPassword().equals(newPassword)){
            Map<String, Object> map = new HashMap<String, Object>();
            HttpSession session = request.getSession();
            String token = TokenUtil.genetateToken();
            session.setAttribute(SESSION_TOKEN_KEY, token);
            session.setAttribute(SESSION_USERNAME_KEY, userName);
            session.setAttribute(SESSION_USERID_KEY, userInfo.getUserUuid());
            session.setMaxInactiveInterval(30 * 60);
            map.put("token", token);
            map.put("userId",userInfo.getUserUuid());
            return map;
        }else{
            return null;
        }
    }

    /**
     * 注册
     * @param userName
     * @param password
     * @return
     */
    public int register(String userName, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UserInfo user = loginDao.findByUserName(userName);
        Assert.notNull(user,"该用户已存在！");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        String newPassword = TokenUtil.EncoderByMd5(password);
        userInfo.setPassword(newPassword);
        userInfo.setUserUuid(IdGenerateUtil.getUUID());
        userInfo.setStatus(1);
        UserInfo save = loginDao.save(userInfo);
        if (save == null){
            return 0;
        }else{
            return 1;
        }

    }
}
