package com.beikai.springboottestdemo.aboutDataFomat;

import com.beikai.springboottestdemo.aboutDataFomat.utils.DateUnit;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateUtils04
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/30 9:11
 * @Version 1.0
 *  使用 ThreadLocal 来保证线程安全的 每个线程创建一个SimpleDateFormat 对象
 **/
public class DateUtils04 {

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(DateUnit.TIME_FORMAT01);
        }
    };

    /**
     * 日期类转换成指定类型字符串
     * @param date
     * @return
     */
    public static String format(Date date){
        return threadLocal.get().format(date);
    }

    /**
     * 指定格式转换成日期类
     * @param strDate
     * @return
     */
    public static Date parse(String strDate){
        Date parse = null;
        try {
            parse = threadLocal.get().parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parse;
    }
}
