package com.project.miu.commons.myEnum;

public enum ResultEnum {
    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(10000,"成功"),
    USER_NOT_EXIST(1,"用户名或密码错误"),
    USER_IS_EXIST(2,"用户已存在"),
    ERROR(3,"用户未登录，请先登录"),
    CATEGORY_UUID_NOT_EXIST(4,"类目ID不存在"),
    PARAM_ERROR(5,"参数错误！"),
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
