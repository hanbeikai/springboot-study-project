package com.beikai.springboottestdemo.writtentest.HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HashMapTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/1 18:30
 * @Version 1.0
 *
 *  一些关于hashmap的测试
 *
 *      1. hashmap是无序的
 *      2. 使用map的keySet()方法进行遍历
 *      3. 使用map.entrySet() 方式进行遍历,方法返回的结果是 Map.entry<string,string> 可以调用getkey 和 getvalue
 **/
public class HashMapTest {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();

        map.put("temp1","张三");
        map.put("temp2","张四");
        map.put("temp3","张五");
        map.put("temp4","张六");

        // 方式一
        for (String s : map.keySet()) {
            System.out.println("key对应的是 : " + s);
            System.out.println("value对应的是 : " + map.get(s));
        }
        // 方式二
        for (Map.Entry<String, String> map2 : map.entrySet()) {
            System.out.println(map2.getKey());
            System.out.println(map2.getValue());
        }
        //方式三
        for (Map.Entry<String, String> map3 : map.entrySet()) {
            System.out.println(map3.toString());
        }


    }


}
