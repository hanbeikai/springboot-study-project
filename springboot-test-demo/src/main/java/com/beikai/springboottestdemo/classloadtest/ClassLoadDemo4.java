package com.beikai.springboottestdemo.classloadtest;

/**
 * @ClassName ClassLoadDemo4
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/1 23:56
 * @Version 1.0
 *
 *1:SingleTon singleTon = SingleTon.getInstance();调用了类的SingleTon调用了类的静态方法，触发类的初始化
 * 2:类加载的时候在准备过程中为类的静态变量分配内存并初始化默认值 singleton=null count1=0,count2=0
 * 3:类初始化化，为类的静态变量赋值和执行静态代码快。singleton赋值为new SingleTon()调用类的构造方法
 * 4:调用类的构造方法后count=1;count2=1
 * 5:继续为count1与count2赋值,此时count1没有赋值操作,所有count1为1,但是count2执行赋值操作就变为0
 *
 * 静态变量的初始化是根据在类中定义的顺序进行的。
 *
 **/
public class ClassLoadDemo4 {
    public static ClassLoadDemo4 classLoadDemo4 = new ClassLoadDemo4();
    public static int num1;
    public static int num2 = 0;

    public ClassLoadDemo4(){
        num1++;
        num2++;
    }

    public static ClassLoadDemo4 getInstance(){
        return classLoadDemo4;
    }

}

class Test{
    public static void main(String[] args) {
        ClassLoadDemo4 instance = ClassLoadDemo4.getInstance();
        System.out.println(instance.num1);
        System.out.println(instance.num2);
    }
}
