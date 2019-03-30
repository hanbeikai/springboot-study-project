package com.beikai.springboottestdemo.aboutDataFomat;

import com.beikai.springboottestdemo.aboutDataFomat.utils.DateUnit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName aboutDataFomat
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/30 8:41
 * @Version 1.0
 *
 *  普通方式创建格式转换类
 *      这种方式在单线程下没问题,但是在多线程下,由于通过static 静态了,所以这个对象不是每一次调用重新创建一个对象,因而是多个线程共享一个,而SimpleDateFormat 没有锁
 *      所以线程不安全
 **/
public class DateUtils01 {

    /**
     * 设置静态对象
     */
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DateUnit.TIME_FORMAT01);

    /**
     * 把日志对象装换成指定格式的字符串
     * @param date
     * @return
     */
    public static String format(Date date){
        return SIMPLE_DATE_FORMAT.format(date);
    }

    /**
     * 把指定格式的日期字符串转换成日期对象
     * @param strDate
     * @return
     */
    public static Date parse(String strDate){

        Date parse = null;
        try {
            parse = SIMPLE_DATE_FORMAT.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

}
