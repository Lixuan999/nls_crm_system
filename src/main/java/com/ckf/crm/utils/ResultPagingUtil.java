package com.ckf.crm.utils;


import com.ckf.crm.model.ResultFormatPaging;

/**
 * @author PIGS-猪农·杨
 * @version 1.0
 * @date 2020/2/17 14:53
 * 统一返回工具类
 */
public class ResultPagingUtil {



    /**
     * 分页返回数据格式
     * 成功
     * @param object
     * @return
     */
    public static ResultFormatPaging pagingSuccess(Integer code, Long count, Object object) {
        ResultFormatPaging result =new ResultFormatPaging();
        result.setCode(code);
        result.setCount(count);
        result.setData(object);
        return result;
    }

    /**
     * 分页返回数据格式
     * 错误
     * @param object
     * @return
     */
    public static ResultFormatPaging pagingError(Integer code,Long count,Object object) {
        ResultFormatPaging result =new ResultFormatPaging();
        result.setCode(code);
        result.setCount(count);
        result.setData(object);
        return result;
    }

}
