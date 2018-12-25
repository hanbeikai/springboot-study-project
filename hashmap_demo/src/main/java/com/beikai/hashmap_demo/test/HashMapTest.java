package com.beikai.hashmap_demo.test;

import org.junit.Test;

import java.util.HashMap;

/**
 * @ClassName HashMapTest
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/23 13:26
 * @Version 1.0
 **/
public class HashMapTest {

    @Test
    public void hashMapTest(){
        /**
         * jdk 1.7 中 hash是怎么计算下标的呢?
         *  int hash = hash(key);  // hash(key) 通过hash() 方法计算出 key的哈希值
         *  int i = indexFor(hash.table.length); // i 就是下标
         *
         *  1.7 中 获取下标的算法是一个 & 算法 配合前面 创建的hashmap中 初始大小是 2的次方数匹配 达到一个效率最高
         *
         *   hash() 中的右移操作 是为了防止低四位 的数据发生多次碰撞  而把高四位的二进制数 参与到比较中
          */

        HashMap<String,String> hashMap = new HashMap();
        hashMap.put("张","三");

        for (String o : hashMap.keySet()) {
            int hashcode = o.hashCode();

            System.out.println(String.format("%s的hashcode是%s",o,hashcode));
        }


    }

}
