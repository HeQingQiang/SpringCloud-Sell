package com.shineyu.order.utils;

import java.util.Random;

/**
 * 主键生成器
 *
 * @author shineYu
 * @Date 19-3-5 下午2:16
 */
public class KeyUtil {

    /**
     * 生成订单主表唯一的主键
     * 格式： DM+时间+随机数
     */
    public static synchronized String genUniqueKeyByOrderMaster(){
        Random random = new Random();
        Integer number = random.nextInt(900) + 100;
        return "OM"+System.currentTimeMillis()+String.valueOf(number);
    }

    /**
     * 生成订单子表唯一的主键
     * 格式： DM+时间+随机数
     */
    public static synchronized String genUniqueKeyByOrderDetail(){
        Random random = new Random();
        Integer number = random.nextInt(900) + 100;
        return "OD"+System.currentTimeMillis()+String.valueOf(number);
    }

}
