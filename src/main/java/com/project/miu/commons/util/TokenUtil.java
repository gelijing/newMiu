package com.project.miu.commons.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TokenUtil {
    //随机数发生器
    public static String genetateToken(){
        String token = System.currentTimeMillis()+"";//获得毫秒数加随机数
        String tokenMd5="";
        try{
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5 = md.digest(token.getBytes());
            BASE64Encoder base =new BASE64Encoder();
            tokenMd5 = base.encode(md5);
        }catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return tokenMd5;
    }
    /**
     * 密码加密
     * 利用MD5进行加密*/
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
}
