package com.beikai.springbootthread.test.threadlocktest;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Test10
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/19 23:31
 * @Version 1.0
 *  通过直接调用 cas 方法进行测试
 **/
public class Test10 {

    volatile int value = 0;

    static Unsafe unsafe;

    static long l;

    static {
        // 由于 unsalf 不能直接 通过new的形式或调用方法的形式创建,所以通过反射的形式创建,调用其方法
        try {
            // 通过反射 获取field对象
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            // 强制设置字段可以通过反射获取
            field.setAccessible(true);
            // 设置要获取的对象  由于unsalf 没有对象, 并且 上述方法是静态的 所以设置为 null
            unsafe = (Unsafe) field.get(null);


            // 修改 value 的值  但是不是通过普通的方式修改, 首先通过当前类定位到对应value的位置,然后进行操作
            l = unsafe.objectFieldOffset(Test10.class.getDeclaredField("value"));


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用执行方法
     */
    public void add(){
        // 为了保证 如果内存中的当前值 与 cas 机制中的当前值冲突后线程可以重复计算,知道满足条件,所以采用do - while 循环的方式
        int current;
        do {
            // 获取内存中当前的值
            current = unsafe.getIntVolatile(this, l);
            // 进行 cas 操作 第一个参数是 当前对象, 第二个参数是 当前值的偏移量, 第三个参数是当前的值, 第四个参数是 要修改的值
            // 当执行上一个操作获取当前值后,调用cas 方法通过通过参数中的当前值与内存中的值是否相同,如果相同,把第四个参数存到内存中,
            // 并返回false,如果不相同,不把第四个参数存在内存中,并且返回false
        }while (!unsafe.compareAndSwapInt(this, l, current, current + 1));
    }

    /**
     * 测试方法
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Test10 test10 = new Test10();

        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < 2; i++) {

            new Thread(() ->{
                for (int j = 0; j < 10000; j++) {
                    test10.add();
                    System.out.println("当前线程是 : " + Thread.currentThread().getName()+"-"+test10.value);
                    if (test10.value == 10000){
                        integers.add(test10.value);
                        System.out.println("--------------------------------------------------");
                    }

                }
            }).start();

        }
        Thread.sleep(1000L);
        System.out.println("结果是 : " + test10.value);

        System.out.println(integers.get(0));
    }
}
