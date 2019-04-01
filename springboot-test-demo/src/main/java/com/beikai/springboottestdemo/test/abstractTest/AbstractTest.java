package com.beikai.springboottestdemo.test.abstractTest;

import com.beikai.springboottestdemo.aboutAbstractClassTest.AbstractClassDemo;
import org.junit.Test;

/**
 * @ClassName AbstractTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/1 19:44
 * @Version 1.0
 **/
public class AbstractTest extends AbstractClassDemo {

    @Override
    public String dosomething() {
        System.out.println("AbstractTest");
        return null;
    }

    public static void main(String[] args) {
        AbstractTest abstractTest = new AbstractTest();
        abstractTest.dosomething();
    }

    public int add(int index){
        return ++index;
    }

    @Test
    public void test(){
        int i = 3;
        int add = add(i);
        System.out.println(add);
    }
}
