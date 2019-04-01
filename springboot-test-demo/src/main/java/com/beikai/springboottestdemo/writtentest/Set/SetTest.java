package com.beikai.springboottestdemo.writtentest.Set;

import java.util.*;

/**
 * @ClassName SetTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/1 18:34
 * @Version 1.0
 *
 *  关于set的遍历
 *   1. 去重
 *   2. 无序 (不是按照放进去的顺序取出的这种无序,但是可以用于排序)
 **/
public class SetTest {
    public static void main(String[] args) {

        Set<String> set = new HashSet<>();
        set.add("张三");
        set.add("张三");
        // for 循环遍历
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println("-----------------------------------");
        // 迭代器遍历
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
        System.out.println("-----------------------------------");
        // 对于string 类型的转换
        Set<Object> set1 = new HashSet<>();
        for (Object o : set1) {
            if (o instanceof Integer){
                Integer i = (Integer) o;
            }else if (o instanceof String){
                String s = (String) o;
            }
        }
        System.out.println("-----------------------------------");
        // set的排序
        Set<Integer> set2 = new HashSet<>();
        set2.add(4);
        set2.add(9);
        set2.add(2);
        set2.add(6);
        set2.add(3);
        System.out.println(set2);
        for (Integer integer : set2) {
            System.out.print(integer + " ");
        }
        System.out.println();
        System.out.println("-----------------------------------------");

        Set<String> strings = new HashSet<>();
        strings.add("20180101");
        strings.add("20180102");
        strings.add("20180106");
        strings.add("20180103");
        strings.add("20180104");
        strings.add("20180105");
        // 使用lambda
        Set<String> set3 = new TreeSet<>((o1, o2) ->o1.compareTo(o2));
        set3.addAll(strings);
        System.out.println(strings.toString());

        //使用set去重
        List<String> strings1 = new ArrayList<>();
        strings1.add("张三");
        strings1.add("李四");
        strings1.add("李四");

        Set set4 = new HashSet(strings1);
        strings1.clear();
        strings1.addAll(set4);

        System.out.println(strings1.toString());
    }
}
