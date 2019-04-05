package com.beikai.springboottestdemo.Reflect;

/**
 * @ClassName User
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/5 15:39
 * @Version 1.0
 **/
public class User {
    public String name;
    private String age;


    static {
        System.out.println("静态代码块被掉用了");
    }

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public User() {
        System.out.println("构造方法被调用了");
    }

    {
        System.out.println("代码块被调用了");
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAll(){
        System.out.println("执行 getAll 方法了");
        System.out.println("name : " + this.name + " --- " + "age : " +this.age);
        return this.name + "---" + this.age;
    }

    public String setUser(String name,String age){
        System.out.println("执行 setUser 方法了");
        this.name = name;
        this.age = age;
        return this.name + "---" + this.age;
    }

    private String showAll(String name,String age){
        System.out.println("执行 showAll 方法了");
        this.name = name;
        this.age = age;
        return this.name + "---" + this.age;
    }

    public static void staticGetAll(String name){
        System.out.println("调用静态方法了 : " + name);
    }

    public static String staticGetAll2(){
        return "调用静态方法2了 ";
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
