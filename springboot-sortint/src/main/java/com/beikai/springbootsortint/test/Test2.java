package com.beikai.springbootsortint.test;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName Test2
 * @Description TODO
 * @Author Admin
 * @Date 2019/2/13 21:41
 * @Version 1.0
 **/
public class Test2 {
    @Test
    public void test1(){
        String s = "123";
        String s1 = "123";

        System.out.println(s == s1);
        System.out.println(s.equals(s1));
    }

    @Test
    public void test2(){
        int j = 0;

        for (int i = 0; i < 100; i++) {
             j = ++j;
        }

        System.out.println(j);
    }

    @Test
    public void test3(){
        System.out.println(System.currentTimeMillis());
        Date n = new Date();
        long time = n.getTime();
        System.out.println(time);
        System.out.println(Calendar.getInstance().getTimeInMillis());

    }

    @Test
    public void test4(){
        Class<Outter> outterClass = null;

        try {
            outterClass = (Class<Outter>) Class.forName("com.beikai.springbootsortint.test.Outter");
            System.out.println(outterClass.getName());
            System.out.println(outterClass.getClass());

            System.out.println(Outter.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5(){
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("test","test");
        concurrentHashMap.put(null,null);
        for (int i = 0; i < 100; i++) {

            concurrentHashMap.put(UUID.randomUUID().toString(),"test");

            System.out.println("大小是 : " + concurrentHashMap.size());
        }
    }

    @Test
    public void test6(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("test","test");
        hashMap.put(null,null);
        for (int i = 0; i < 100; i++) {

            hashMap.put(UUID.randomUUID().toString(),"test");

            System.out.println("大小是 : " + hashMap.size());
        }
    }

    @Test
    public void test7(){
        Hashtable<String,String> hashtable = new Hashtable<>();
        hashtable.put("test","test");

        hashtable.put(null,null);
        for (int i = 0; i < 100; i++) {

            hashtable.put(UUID.randomUUID().toString(),"test");

            System.out.println("大小是 : " + hashtable.size());
        }

        ArrayList arrayList = new ArrayList();


    }

    @Test
    public void test(){
        HashMap hashMap = new HashMap();
        Map map = Collections.synchronizedMap(hashMap);


    }
}
