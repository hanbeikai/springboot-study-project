package com.beikai.springboottestdemo.aboutDataFomat;

import com.beikai.springboottestdemo.aboutDataFomat.utils.DateUnit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateUtils03
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/30 9:06
 * @Version 1.0
 *  通过加锁的方式 实现线程的安全
 *
 *      但是如果并发量大的情况下,会再次线程阻塞
 **/
public class DateUtils03 {

    /**
     * 创建对象
     */
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DateUnit.TIME_FORMAT01);

    /**
     * 日期类转换成指定类型字符串
     * @param date
     * @return
     */
    public static String format(Date date){
        synchronized (SIMPLE_DATE_FORMAT){
            return SIMPLE_DATE_FORMAT.format(date);
        }
    }

    /**
     * 指定格式转换成日期类
     * @param strDate
     * @return
     */
    public static Date parse(String strDate){
        synchronized (SIMPLE_DATE_FORMAT){
            Date parse = null;
            try {
                parse = SIMPLE_DATE_FORMAT.parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return parse;
        }
    }
}
