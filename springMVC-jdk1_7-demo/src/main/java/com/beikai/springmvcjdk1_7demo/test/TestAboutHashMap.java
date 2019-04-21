package com.beikai.springmvcjdk1_7demo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName TestAboutHashMap
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/16 7:42
 * @Version 1.0
 **/
public class TestAboutHashMap {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("zhang","zhangsan");

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("ssss","ddd");

        int i = 1 << 30;

        System.out.println(i);

        System.out.println(Integer.MAX_VALUE);

        int i2 = 16;

        System.out.println(Integer.highestOneBit((i2-1) << 1));

        ArrayList list = new ArrayList();
        list.add("张飒");

        LinkedList linkedList = new LinkedList();
        linkedList.add("张三");
    }
}
