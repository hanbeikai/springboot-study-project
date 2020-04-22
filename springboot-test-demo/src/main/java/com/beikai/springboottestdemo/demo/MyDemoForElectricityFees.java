package com.beikai.springboottestdemo.demo;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2019/10/23
 * Time: 11:07 下午
 * Description: No Description
 */

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 计算电费 demo
 */
public class MyDemoForElectricityFees {

    public static void main(String[] args) {
        // 输入录入时间
        LocalDate now = LocalDate.now();
        // 输入上次记录时间
        String beforeDate = "2020-04-01";
        // 输入总电费
        BigDecimal totalFees = new BigDecimal("287.29");
        // 输入上次左侧表数
        BigDecimal leftBeforeUse = new BigDecimal("15817");
        // 输入上次右侧表数
        BigDecimal rightBeforeUse = new BigDecimal("13398");
        // 输入当前左侧表数
        BigDecimal leftCurrUse = new BigDecimal("15913");
        // 输入当前右侧表数
        BigDecimal rightCurrUse = new BigDecimal("13535");

        System.out.println("=====================> 展示信息 <=====================");
        System.out.println("本月电费计算时间 ：" + now);
        System.out.println("上月电费计算时间 ：" + beforeDate);
        System.out.println("本月电费总费用 ： " + totalFees.doubleValue());
        System.out.println("上月进门右侧房间总电量 ：" + leftBeforeUse.intValue());
        System.out.println("本月进门右侧房间总电量 ：" + leftCurrUse.intValue());
        System.out.println("上月进门直走房间总电量 ：" + rightBeforeUse.intValue());
        System.out.println("本月进门直走房间总电量 ：" + rightCurrUse.intValue());
        System.out.println("=====================> 开始计算 <=====================");

        BigDecimal currLeftUse = leftCurrUse.subtract(leftBeforeUse);
        BigDecimal currRightUse = rightCurrUse.subtract(rightBeforeUse);

        System.out.println("本月进门右侧房间用电量 ：" + currLeftUse.intValue());
        System.out.println("本月进门直走房间用电量 ：" + currRightUse.intValue());

        BigDecimal currTotalUse = currLeftUse.add(currRightUse);

        BigDecimal leftFeesUse = totalFees.multiply(currLeftUse).divide(currTotalUse,2,BigDecimal.ROUND_HALF_UP);
        BigDecimal rightFeesUse = totalFees.multiply(currRightUse).divide(currTotalUse,2,BigDecimal.ROUND_HALF_UP);

        System.out.println("本月进门右侧房间电费 ： " + leftFeesUse.doubleValue());
        System.out.println("本月进门直走房间电费 ： " + rightFeesUse.doubleValue());

        System.out.println("=====================> 结束计算 <=====================");

    }
}
