package com.beikai.springboottestdemo.Reflect;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.util.Properties;

/**
 * @ClassName Test01
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/5 9:04
 * @Version 1.0
 * <p>
 * 反射:
 * 反射就是利用字节码文件,获取对象的属性,字段,方法,构造方法的类的内部内容,根据字节码文件创建对象,调用对象方法的技术
 * <p>
 * 反射的基础是 class
 * <p>
 * <p>
 * 如何获取class对象,有三种方法:
 * 1. 通过每个类的class属性
 * 2. 通过getClass()方法
 * 3. 通过Class.forName(完整类名)方法
 **/
public class Test01 {
    /**
     * 抛出 ClassNotFoundException 的原因是 防止 Class.forName() 中的类全名是瞎写的
     *
     * @param args
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {

        // 选择要执行的方法
        switchMethod(6);


    }

    private static void switchMethod(int num) throws ClassNotFoundException {
        switch (num) {
            case 1:
                // 获取 class 字节码对象的三种方式
                getClassMethod();
                break;
            case 2:
                // 关于 class中 泛型的 测试
                aboutClassSomething();
                break;
            case 3:
                // 关于字节码的方法的加载问题测试
                aboutClassLoading();
                break;
            case 4:
                // 关于类的信息
                aboutClassContent();
                break;
            case 5:
                // 通过反射获取类内部属性
                aboutClassAttributeAndMethod();
                break;
            case 6:
                // 通过配置文件获取字节对象
                aboutGetClassFromProperties();
                break;
            case 7:
                // 通过反射 使用构造器 创建对象
                aboutClassConstructor();
                break;
            default:
                break;

        }
    }

    /**
     * 通过构造器创建对象
     */
    private static void aboutClassConstructor() {

        try {
            // 1. 创建字节码对象
            Class<?> test07_01 = Class.forName("com.beikai.springboottestdemo.Reflect.User");
            System.out.println("-------------------通过无参构造器方式创建对象--------------------------");
            // 2.1 获取构造对象
            Constructor constructor = test07_01.getConstructor(null);
            // 2.2 通过newInstance 创建对象
            Object test07_02 = constructor.newInstance(null);
            // 2.3 打印user
            System.out.println(test07_02);
            System.out.println("-------------------通过有参构造器方式创建对象--------------------------");

            // 3.1 获取构造器对象
            Constructor<?> test07_03 = test07_01.getConstructor(String.class, String.class);
            // 3.2 创建对象,设置初始值
            Object test07_04 = test07_03.newInstance("贾宝玉", "2");
            // 3.3 打印对象
            System.out.println(test07_04);


        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射从配置文件中读取对象路径,创建对象
     */
    private static void aboutGetClassFromProperties() {
        InputStream inputStream = null;
        try {
            // 1. 读取配置文件中信息
            Properties properties = new Properties();
            // 2. 创建输入流
            inputStream = Test01.class.getResourceAsStream("/properties/config.properties");
            // 3. 加载文件信息
            properties.load(inputStream);
            // 4. 获取文件信息
            String classname = properties.getProperty("classname");
            // 5. 获取字节对象
            Class<?> aClass = Class.forName(classname);
            // 6. 实例对象
            Object object = aClass.newInstance();



        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过反射获取类的内部属性和方法
     *
     *  通过getXxx() 获取的是共有的
     *  通过getDeclaredXxx() 获取的是已有的,共有和私有都可以获取
     */
    private static void aboutClassAttributeAndMethod() {
        try {
            // 创建字节码对象
            Class<User> test05_01 = User.class;
            // 1. 获取反射字段
            Field name = test05_01.getField("name");
            // 2. 实例化对象
            User user = test05_01.newInstance();

            System.out.println("------------------反射属性(共有)-----------------");

            // 3. 设置字段的值 参数1 是对象  参数2 是参数值
            name.set(user,"张三");
            // 4. 获取制定属性的值
            System.out.println(name.get(user));
            // 5. 获取对象的值
            System.out.println(user);

            System.out.println("------------------反射属性(私有)-----------------");

            // 6. 获取私有属性的值
            Field age = test05_01.getDeclaredField("age");
            // 6.1 设置私有属性可见性
            age.setAccessible(true);
            // 6.2 设置私有属性的值
            age.set(user,"23");
            // 6.3 打印
            System.out.println(age.get(user));
            System.out.println(user);

            System.out.println("------------------反射方法(无参)-----------------");

            // 7. 获取方法(无参)  第一个参数传递 方法名  第二个参数 传递 参数类型  如果没有 传递 null
            Method getAll = test05_01.getMethod("getAll", null);
            // 7.1 调用方法  第一个参数 实例名, 第二个参数 方法的实参  没有传递 null  如果有返回值,返回一个对象
            Object invoke = getAll.invoke(user, null);
            // 7.2 打印
            System.out.println(invoke);

            System.out.println("------------------反射方法(有参,共有)-----------------");

            // 9 获取方法(共有,有参),
            Method setUser = test05_01.getMethod("setUser", String.class, String.class);
            // 9.1 执行方法
            Object invoke2 = setUser.invoke(user, "程咬金", "55");
            // 9.3 打印结果
            System.out.println(invoke2);
            System.out.println(user);

            System.out.println("------------------反射方法(有参,私有)-----------------");

            // 8. 获取方法(有参,私有), 第一个参数是 方法名,第二个之后 参数是 入参的类型
            Method showAll = test05_01.getDeclaredMethod("showAll", String.class, String.class);
            // 8.1 设置私有方法可见性
            showAll.setAccessible(true);
            // 8.2 执行方法
            Object invoke1 = showAll.invoke(user, "王五", "44");
            // 8.3 打印返回的记结果
            System.out.println(invoke1);
            System.out.println(user);

            System.out.println("------------------反射静态方法(有参)-----------------");

            // 10. 获取静态方法
            Method staticGetAll = test05_01.getMethod("staticGetAll", String.class);
            // 10.1 执行方法  静态方法 第一个对象 传null
            Object invoke3 = staticGetAll.invoke(null, "张三");
            // 10.2 获取执行结果
            System.out.println(invoke3);

            System.out.println("------------------反射静态方法(无参)-----------------");

            // 11. 获取静态方法
            Method staticGetAll2 = test05_01.getMethod("staticGetAll2", null);
            // 11.1 执行方法  静态方法 第一个对象 传null  无参的话第二个参数也为 null
            Object invoke4 = staticGetAll2.invoke(null, null);
            // 11.2 获取执行结果
            System.out.println(invoke4);


        } catch (NoSuchFieldException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 关于类的信息的方法
     */
    private static void aboutClassContent() {
        // 创建类的对象
        Class<?> test04_01 = String.class;

        StringBuilder stringBuilder = new StringBuilder();

        // 反射类的信息
        // 1. 获取类的修饰符  test04_01.getModifiers() 获取的是修饰符对应的整形, 需要调用modifier 的toString() 转换
        int modifiers = test04_01.getModifiers();
        String modify = Modifier.toString(modifiers);
        stringBuilder.append("修饰符是 : ").append(modify).append("\r\n");
        // 2. 获取列的类名
        stringBuilder.append("类名是 : ").append(test04_01.getName()).append("\r\n").append("简单类名 : ").append(test04_01.getSimpleName()).append("\r\n");
        // 3. 获取父类
        Class<?> test04_02 = test04_01.getSuperclass();
        // 如果父类是 object 就不添加了
        if (Object.class != test04_02){
            stringBuilder.append("继承的父类是 : ").append(test04_02.getSimpleName()).append("\r\n");
        }
        // 4. 获取实现类  如果没有,返回的数组长度为 0
        Class<?>[] test04_03 = test04_01.getInterfaces();
        if (test04_03.length > 0) {

            stringBuilder.append("实现的类是 : ");
            for (int i = 0; i < test04_03.length; i++) {
                stringBuilder.append(test04_03[i].getSimpleName());
                if (i < test04_03.length - 1) {
                    stringBuilder.append(",");
                }else {
                    stringBuilder.append("\r\n");
                }
            }

        }
        // 打印结果
        System.out.println(stringBuilder);
    }

    private static void aboutClassLoading() {
        /**
         *  类.class 与 Class.forName() 的区别
         *
         *  使用 Class.forName() 的时候,会把参数指定的类加载到内存中
         *  而 类.class不会
         *
         *  Class<?> test03_1 = User.class;
         *      没有执行 user 中的方法,所以不会加载到内存中的
         *
         *  Class.forName("com.beikai.springboottestdemo.Reflect.User");
         *      执行了user中的静态方法,所以会加载到内存中的
         *
         *      通常被反射的类 都提供了构造函数
         */
        Class<?> test03_1 = User.class;

        System.out.println("--------------------------------------------------------");

        try {
            Class<?> test03_2 = Class.forName("com.beikai.springboottestdemo.Reflect.User");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private static void aboutClassSomething() {
        /**
         * 关于 class<?></> 的泛型
         */

        Class<?> test02_1 = int.class;
        Class<?> test02_2 = Integer.class;

        // 因为 int 对应的是基本类型, integer 对应的是 包装类型
        System.out.println(test02_1 == test02_2); // false
        // integer 的 type 对应的是 这个包装类的基本类型
        Class<?> test02_3 = Integer.TYPE;
        System.out.println(test02_1 == test02_3); //true
    }

    private static void getClassMethod() throws ClassNotFoundException {
        // 1. 通过每个类的 class 属性
        Class test01_1 = Test01.class;
        // 2. 通过每个类的 getClass()方法
        Class test01_2 = new Test01().getClass();
        // 3. 通过Class.forName(完整类名)方法
        Class test01_3 = Class.forName("com.beikai.springboottestdemo.Reflect.Test01");

        /**
         * test01_1==test01_2 为ture的原因是 两个对象指向的是同一个字节码对象
         */
        System.out.println(test01_1);           // class com.beikai.springboottestdemo.Reflect.Test01
        System.out.println(test01_1 == test01_2);  //true
        System.out.println(test01_2 == test01_3);  // true
    }

}

