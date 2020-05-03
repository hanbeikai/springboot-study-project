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
 * 完成时间转换和格式化
 * 在多线程情况下会导致由于多线程问题导致获取的时间不同
 * <p>
 * 解决方法2 :
 * <p>
 * 创建工具类 使用 threadLocal 对象实现, 这个类可以把线程与对象绑定起来,  使用threadLock 对象 包装simpleDateFormat ,使其达到线程的安全
 *
 *         原因是 在 有线程 使用 threadLocal 变量的时候,threadLocal会为这个线程创建一个变量的副本,这个副本只针对这个线程使用,不会对这个变量的其他副本有作用,所以可以保证变量的原子性
 *
 *
 */
public class SimpleDateFormatDemo03 {

    public static void main(String[] args) {
        String[] times = new String[]{"2020-05-01","2020-05-02","2020-05-03","2020-05-04","2020-05-05","2020-05-06","2020-05-07","2020-05-08","2020-05-09"};

        Mythread04[] mythread02s = new Mythread04[times.length];
        int i = 0;
        for (String time : times) {
            Mythread04 m = new Mythread04(time,"yyyy-MM-dd");
            mythread02s[i++] = m;
        }

        for (Mythread04 mythread02 : mythread02s) {
            mythread02.start();
        }

    }
}


class Mythread04 extends Thread {

    private String format;
    private String time;

    public Mythread04(String time,String format ) {
        this.format = format;
        this.time = time;
    }

    @SneakyThrows
    @Override
    public void run() {
        // 字符串 转换程 日期
        Date parse = DateUtil.getThreadLocal(format).parse(time);
        // 日期转换成字符串
        String format1 = DateUtil.getThreadLocal(format).format(parse);

        System.out.println("hashcode : " + DateUtil.getThreadLocal(format).hashCode());

        // 字符串相比较 是否相同
        if (time.equals(format1)) {
            System.out.println("录入的时间是 : " + time + " 转换后的时间是 : " + format1);
        }
    }
}

// 创建工具类 使用 threadLocal 对象实现, 这个类可以把线程与对象绑定起来, 当有线程调用时,会为这个线程创建一个对应的副本
class DateUtil {
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

    // 获取指定格式的对象
    public static SimpleDateFormat getThreadLocal(String format){
        SimpleDateFormat simpleDateFormat = null;
        simpleDateFormat = threadLocal.get();
        if (null == simpleDateFormat) {
            simpleDateFormat = new SimpleDateFormat(format);
            threadLocal.set(simpleDateFormat);
        }

        return simpleDateFormat;

    }
}