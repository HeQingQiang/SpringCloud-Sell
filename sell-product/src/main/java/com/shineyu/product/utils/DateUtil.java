package com.shineyu.product.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author shine95
 * @Date 19-3-18 上午12:59
 */
public class DateUtil {

    /**
     * 获取当前时间=年月日 时分秒
     *
     * @return 时间字符串
     */
    public static String getNowDateTime(){
        Date time = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(time);
    }

}
