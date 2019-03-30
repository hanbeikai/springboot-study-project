package com.beikai.springboottestdemo.aboutDataFomat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateUtils02
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/30 8:58
 * @Version 1.0
 * 通过每次调用创建一个线程的方式
 *  这种方式可以解决 线程安全问题,但是每一次调用都创建一个对象和销毁一个对象,效率低
 **/
public class DateUtils02 {

    /**
     * 日期类型转换成 指定格式的字符串
     * @param date   被转换日志
     * @param type   目标类型
     * @return
     */
    public static String format(Date date, String type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        return simpleDateFormat.format(date);
    }

    /**
     * 指定格式的字符串转换成日期对象
     * @param strDate   要转换的日期字符串
     * @param type      字符串的日期格式
     * @return
     */
    public static Date parse(String strDate, String type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
