package com.beikai.springboottestdemo.test;

/**
 * @ClassName Test06
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/15 7:49
 * @Version 1.0
 **/
public class Test06 {
    public static void main(String[] args) {

        new Test06().first();
    }

    public void first(){
        int i = 5;
        Value2 value2 = new Value2();
        value2.i = 25;
        second(value2,i);
        System.out.println(value2.i);

    }

    public void second(Value2 value2,int i){
        i = 3;
        value2.i = 15;
        Value2 value21 = new Value2();
        value2 = value21;
        System.out.println(value2.i + "=" + i);
    }
}

class Value2{
    public int i = 10;
}
