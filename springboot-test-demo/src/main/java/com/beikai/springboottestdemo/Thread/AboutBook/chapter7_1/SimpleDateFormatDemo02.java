package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 7:05 下午
 * Description: 通过 simpleDateFormat
 *      完成时间转换和格式化
 *      在多线程情况下会导致由于多线程问题导致获取的时间不同
 *
 *     解决的方式1 :
 *          使用工具类,每个线程有自己的 simpleDateFormat 对象,可以避免出现多线程通用以 simpleDateFormat 对象 导致时间不同的问题
 *
 *     如下面的
 *          DateUtils 工具类
 *
 */
public class SimpleDateFormatDemo02 {

    public static void main(String[] args) {
        String[] times = new String[]{"2020-05-01","2020-05-02","2020-05-03","2020-05-04","2020-05-05","2020-05-06","2020-05-07","2020-05-08","2020-05-09"};

        Mythread03[] mythread02s = new Mythread03[times.length];
        int i = 0;
        for (String time : times) {
            Mythread03 m = new Mythread03(time,"yyyy-MM-dd");
            mythread02s[i++] = m;
        }

        for (Mythread03 mythread02 : mythread02s) {
            mythread02.start();
        }


        /**
         * 录入的时间是 : 2020-05-05 转换后的时间是 : 2020-05-05
         * 录入的时间是 : 2020-05-06 转换后的时间是 : 2020-05-06
         * 录入的时间是 : 2020-05-08 转换后的时间是 : 2020-05-08
         * 录入的时间是 : 2020-05-07 转换后的时间是 : 2020-05-07
         * 录入的时间是 : 2020-05-09 转换后的时间是 : 2020-05-09
         * 录入的时间是 : 2020-05-02 转换后的时间是 : 2020-05-02
         * 录入的时间是 : 2020-05-03 转换后的时间是 : 2020-05-03
         * 录入的时间是 : 2020-05-01 转换后的时间是 : 2020-05-01
         * 录入的时间是 : 2020-05-04 转换后的时间是 : 2020-05-04
         *
         *
         * 从打印的结果可以看出,转换前和转换后的时间是相同的,
         */
    }
}

class Mythread03 extends Thread{
    private String format;
    private String time;

    public Mythread03( String time,String format) {
        this.format = format;
        this.time = time;
    }

    @SneakyThrows
    @Override
    public void run() {
        // 字符串 转换程 日期
        Date parse = DateUtils.parse(time,format);
        // 日期转换成字符串
        String format1 = DateUtils.format(parse,format);

        // 字符串相比较 是否相同
        if (time.equals(format1)) {
            System.out.println("录入的时间是 : " + time + " 转换后的时间是 : " + format1);
        }
    }
}

class DateUtils {

    @SneakyThrows
    public static Date parse(String time, String format){
        return new SimpleDateFormat(format).parse(time);
    }

    public static String format(Date time,String format){
        return new SimpleDateFormat(format).format(time);
    }

}