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
 *      录入的时间是 : 2020-05-07 转换后的时间是 : 2019-12-07
     * 录入的时间是 : 2020-05-09 转换后的时间是 : 2019-12-07
     * 录入的时间是 : 2020-05-08 转换后的时间是 : 2019-12-07
     * Exception in thread "Thread-2" Exception in thread "Thread-5" Exception in thread "Thread-1" Exception in thread "Thread-0" Exception in thread "Thread-3" Exception in thread "Thread-4" java.lang.NumberFormatException: multiple points
     * 	at sun.misc.FloatingDecimal.readJavaFormatString(FloatingDecimal.java:1890)
     * 	at sun.misc.FloatingDecimal.parseDouble(FloatingDecimal.java:110)
     * 	at java.lang.Double.parseDouble(Double.java:538)
     * 	at java.text.DigitList.getDouble(DigitList.java:169)
     * 	at java.text.DecimalFormat.parse(DecimalFormat.java:2089)
     * 	at java.text.SimpleDateFormat.subParse(SimpleDateFormat.java:1869)
     * 	at java.text.SimpleDateFormat.parse(SimpleDateFormat.java:1514)
     * 	at java.text.DateFormat.parse(DateFormat.java:364)
     * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1.Mythread02.run(SimpleDateFormatDemo01.java:51)
 *
 * 	从上面打印额可以看出, 多线程情况下回出现日期不正确, 甚至有可能抛出异常
 *
 * 	    错误的原因是多个线程使用了同一个 simpleDateFormat 对象用来进行日期的转换,但是 simpleDateFormat 本身不是线程安全的,所以会导致多个线程使用这个对象的会后出现错误
 *
 *
 */
public class SimpleDateFormatDemo01 {

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[] times = new String[]{"2020-05-01","2020-05-02","2020-05-03","2020-05-04","2020-05-05","2020-05-06","2020-05-07","2020-05-08","2020-05-09"};

        Mythread02[] mythread02s = new Mythread02[times.length];
        int i = 0;
        for (String time : times) {
            Mythread02 m = new Mythread02(simpleDateFormat,time);
            mythread02s[i++] = m;
        }

        for (Mythread02 mythread02 : mythread02s) {
            mythread02.start();
        }
    }
}

class Mythread02 extends Thread{

    private SimpleDateFormat simpleDateFormat;
    private String time;

    public Mythread02(SimpleDateFormat simpleDateFormat, String time) {
        this.simpleDateFormat = simpleDateFormat;
        this.time = time;
    }

    @SneakyThrows
    @Override
    public void run() {
        // 字符串 转换程 日期
        Date parse = simpleDateFormat.parse(time);
        // 日期转换成字符串
        String format = simpleDateFormat.format(parse);

        // 字符串相比较 是否相同
        if (!time.equals(format)) {
            System.out.println("录入的时间是 : " + time + " 转换后的时间是 : " + format);
        }
    }
}