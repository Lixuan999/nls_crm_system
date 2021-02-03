package com.ckf.crm.utils;

import com.ckf.crm.model.ResultFormat;

/**
 * @author xuan
 * @version 1.0
 * @date 2021/1/17 14:53
 * 统一返回工具类
 */
public class ResultUtil {


    public static ResultFormat success(Object object) {
        ResultFormat result = new ResultFormat();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static ResultFormat success(Integer code, String msg, Object object) {
        ResultFormat result = new ResultFormat();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    public static ResultFormat success(Integer code, String msg) {
        ResultFormat result = new ResultFormat();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static ResultFormat success(String msg) {
        ResultFormat result = new ResultFormat();
        result.setCode(200);
        result.setMsg(msg);
        return result;
    }

    public static ResultFormat success(String msg, Object object) {
        ResultFormat result = new ResultFormat();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    public static ResultFormat success() {
        ResultFormat result = new ResultFormat();
        result.setCode(200);
        result.setMsg("成功");
        return result;
    }

    public static ResultFormat error(Integer code, String msg) {
        ResultFormat result = new ResultFormat();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
