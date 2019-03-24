package com.beikai.springbootthread.test.aboutCreateThread;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ThreadAboutStreamTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/3 17:52
 * @Version 1.0
 *  利用 java8 新特性 stream 实现并发
 **/
public class ThreadAboutStreamTest {

    public static void main(String[] args) {
        List<Integer> va = Arrays.asList(10,49,24,98,45);

        int sum = va.parallelStream().mapToInt(s -> s * 2).sum();

        System.out.println("结果是 : " + sum);

        // 证明是并发  因为他们并不是按照顺序输出的
        va.parallelStream().forEach(p -> System.out.println(p));

    }

}
