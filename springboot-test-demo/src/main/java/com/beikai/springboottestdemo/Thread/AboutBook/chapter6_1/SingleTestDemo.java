package com.beikai.springboottestdemo.Thread.AboutBook.chapter6_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/17
 * Time: 8:01 下午
 * Description: No Description
 */
public class SingleTestDemo {

    public enum EnumSingleDemo{
       singleDemo;
       private EnumSingleDemo(){
       }
       private EnumSingleDemo getSingleDemo(){
           return singleDemo;
       }
   }
   public static EnumSingleDemo doSomething(){
     return EnumSingleDemo.singleDemo.getSingleDemo();
   }
}

class TestDemo{
    public static void main(String[] args) {
        SingleTestDemo.EnumSingleDemo enumSingleDemo = SingleTestDemo.doSomething();
        System.out.println(enumSingleDemo.hashCode());
        SingleTestDemo.EnumSingleDemo enumSingleDemo1 = SingleTestDemo.doSomething();
        System.out.println(enumSingleDemo1.hashCode());
    }
}


