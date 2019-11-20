package com.project.miu.commons.util;

import com.project.miu.commons.myEnum.ResultEnum;
import com.project.miu.commons.util.Result;

public class ResultUtil {
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }
    public static Result success(){
        return success(null);
    }
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    public static Result error(){
        Result result = new Result();
        result.setCode(ResultEnum.PARAM_ERROR.getCode());
        result.setMsg(ResultEnum.PARAM_ERROR.getMsg());
        return result;
    }
    public static Result error(String msg){
        Result result = new Result();
        result.setCode(ResultEnum.PARAM_ERROR.getCode());
        result.setMsg(msg);
        return result;
    }
}
