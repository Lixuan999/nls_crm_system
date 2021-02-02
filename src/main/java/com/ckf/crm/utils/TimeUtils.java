package com.ckf.crm.utils;

import io.swagger.annotations.ApiModel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 安详的苦丁茶
 * @version 1.0
 * @date 2020/3/10 23:50
 * <p>
 * 时间 工具类
 */
@ApiModel(value = "时间工具类")
public class TimeUtils {

    public static String dateTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeFormat = simpleDateFormat.format(date);
        return timeFormat;
    }


}
