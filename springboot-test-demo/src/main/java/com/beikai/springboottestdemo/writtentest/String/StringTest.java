package com.beikai.springboottestdemo.writtentest.String;

/**
 * @ClassName StringTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/1 18:19
 * @Version 1.0
 *  创建 关于 string 的测试题
 **/
public class StringTest {

    public static void main(String[] args) {

        String test1 = new String("张三");
        String test2 = new String("张三");
        System.out.println(test1 == test2);  // false
        System.out.println(test1.equals(test2));  // true

        /**
         *  String test1 = new String("张三"); 这种方式会产生两个对象, 一个是 "张三" 在 String pool中产生的一个值为 "张三" 的对象,另一个是在堆中产生一个值为"张三"
         *  的对象,这个对象是String Pool中的"张三"的拷贝,上述中test1 最后指向的是 堆中的 对象的地址,所以,当两个new String() 对象进行==比较的时候,是堆中的两个对象
         *  的地址的比较,所以, test1 == test2 为false
         */

        System.out.println("------------------------");

        String test3 = "2";
        String test4 = "2";

        System.out.println(test3 == test4);   // true
        System.out.println(test3.equals(test4));  // true
        /**
         * String test3 = "2"; 会在String pool中存创建一个 "2" 的字符串对象,并把地址给 test3
         * String test4 = "2"; 会在Stirng pool中找有没有"2"这个字符串,如果没有,创建一个,如果有的话,返回这个已存在的字符串对象的地址
         * 所以,test3和test4都指向通过同一个String pool中字符串对象的地址,所以 == 比较 为true
         */


        System.out.println("---------------------------------------");

        String hello = "hello";
        String hel = "hel";
        String lo = "lo";

        System.out.println(hello == "hel" + "lo"); // true
        System.out.println(hello == "hel" + lo);   // false

        System.out.println(hello.equals("hel" + "lo"));  // true
        System.out.println(hello.equals("hel" + lo));   // true

        /**
         * 当 声明的对象是类似于 String s = "hello"; 这种形式的,是在spring pool中创建一个 "hello" 的常量对象,当下一次有 一个类似于String s1 = "hello";
         * 的时候,会先从spring pool 中找是否已经存在"hello" 这个常量对象(equal()),如果已经存在,返回这个地址,如果不存在,创建一个,返回地址.
         *       如果是以 string s3 = "hel" + "lo"; 的形式声明字符串,会先把右面的拼接在一起,产生一个新的常量对象"hello",然后去String pool中查,如果已经存在,
         *       返回的是已经存在的 "hello" 的地址,所以,当 变量hello 通过 == 比较的时候,两边的地址相同,所以为true
         *  当声明的对象= 右面两个参数中有一个是对象或两个都是对象 的形式 例如:
         *      String s4 = "hel";
         *      String s5 = "lo";
         *      String s6 = "hel"+s5; 或 String s7 = s4 + s5;
         *      此时会在堆中新建一个对象,这个对象的值是右面两个参数拼接后的值,返回的是堆中新的对象的地址
         *      所以与 变量 hello 的地址不同所以为false
         */

        System.out.println("-----------------------------");

        String test5 = "张三";
        System.out.println(test5 == test2);  // false
        System.out.println(test5.equals(test2)); // true
        /**
         * String test5 = "张三"; 中的 test5 保存的是String pool中的地址
         * String test2 = new String("张三"); 中test2 保存的是 堆中的地址,所以两者通过 == 比较,为false,但是使用equal()时比较的是 不同地址上内容的比较,所以 为true
         */

        System.out.println("-----------------------------");

        String test6 = "ab";
        String test7 = "cd";
        String test8 = "ab" + "cd";
        String test9 = "abcd";
        String test10 = test6 + test7;

        System.out.println(test8 == test9); // true
        System.out.println(test8 == test10); // false
        System.out.println(test9 == test10); // false

        /**
         * String test10 = test6 + test7; 会在堆中产生一值为"abcd" 的对象,而 String test9 = "abcd";这种是在String pool 中产生一个字符串对象,返回的值String pool中的
         * 地址,所以这个比较两个不同地方的地址,答案肯定是不同的了
         */

        System.out.println("-----------------------");
        System.out.println(test8.equals(test9));  // true
        System.out.println(test8.equals(test10));  // true
        System.out.println(test9.equals(test10));  // true
        /**
         * String 中的 equal 比较的是两个对象地址所指向的内容的比较,
         *      源码中,先通过 == 比较引用,如果相同,直接返回true,判断被比较的是否是字符串,如果不是,直接返回false
         *      然后比较两个对象内容的长度,如果不同,返回false,然后再字符串一个一个内容的比较,有不同的,返回false
         *      最后,内容相同,则说明两个内容相同
         */

    }
}
