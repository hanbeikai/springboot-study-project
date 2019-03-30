package com.beikai.springboottestdemo.aboutDataFomat;

import com.beikai.springboottestdemo.aboutDataFomat.utils.DateUnit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DateUtils05
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/30 9:16
 * @Version 1.0
 *  使用 DateTimeFormatter 方式创建
 *   这个方式是线程安全的   阿里推荐使用的方式
 **/
public class DateUtils05 {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DateUnit.TIME_FORMAT01);

    /**
     *  日期转换成字符串
     * @param date
     * @return
     */
    public static String format(LocalDateTime date){
        return DATE_TIME_FORMATTER.format(date);
    }

    /**
     * 字符串转换成 日期
     * @param strDate
     * @return
     */
    public static LocalDateTime parse(String strDate){
        return LocalDateTime.parse(strDate,DATE_TIME_FORMATTER);
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 2; i++) {
            executorService.execute(()->{
                for (int j = 0; j < 100; j++) {

                    System.out.println(Thread.currentThread().getName() +"-"+DateUtils05.format(LocalDateTime.now()));
                    
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
