package com.beikai.springboottestdemo.IO.Object;

import com.beikai.springboottestdemo.IO.util.AddressUtils;

import java.io.*;

/**
 * @ClassName Test06AboutObjectInputStreamAndObjectOutputStream
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/3 21:19
 * @Version 1.0
 *
 *  对象的输入输出流  涉及到序列化
 *
 *   场景描述:
 *      把对象序列化之后,在没有生成serialVersionUID 的前提下,又添加一个字段,在发序列化的时候容易报错:
 *      java.io.InvalidClassException: com.beikai.springboottestdemo.IO.Object.User; local class incompatible: stream classdesc serialVersionUID = 5007364209808485824, local class serialVersionUID = -71765399825409547
 * 	    at java.io.ObjectStreamClass.initNonProxy(ObjectStreamClass.java:687)
 *  	at java.io.ObjectInputStream.readNonProxyDesc(ObjectInputStream.java:1876)
 * 	    at java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:1745)
 * 	    at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2033)
 * 	    at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1567)
 * 	    at java.io.ObjectInputStream.readObject(ObjectInputStream.java:427)
 * 	    at com.beikai.springboottestdemo.IO.Object.Test06AboutObjectInputStreamAndObjectOutputStream.reader(Test06AboutObjectInputStreamAndObjectOutputStream.java:42)
 * 	    at com.beikai.springboottestdemo.IO.Object.Test06AboutObjectInputStreamAndObjectOutputStream.main(Test06AboutObjectInputStreamAndObjectOutputStream.java:27)
 *
 *      原因是 如果不生成serialVersionUID 字段, 在把对象写入文件的时候,会自动生成一个serialVersionUID(隐式),在从文件中读取之前如果对象新添加了一个字段,则
 *      在生成对象的时候生成的serialVersionUID与没修改新字段之前是不同的,这就导致在生成对象的时候回生成失败,
 *
 *      解决的办法是保证 serialVersionUID 的值相同,所以可以创建一个固定的 serialVersionUID 值
 **/
public class Test06AboutObjectInputStreamAndObjectOutputStream {

    public static void main(String[] args) {
        User user = new User().setUsername("张三").setPassword("123456");

        writer(user);

        reader(AddressUtils.getAddress(6));
    }

    /**
     * 读取文件中的对象
     * @param address
     */
    private static void reader(String address) {

        InputStream inputStream = null;
        // 创建文件读取流对象
        ObjectInputStream objectInputStream = null;
        try {
            inputStream = new FileInputStream(address);
            objectInputStream = new ObjectInputStream(inputStream);

            Object object = objectInputStream.readObject();

            System.out.println(object);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 写入文件中对象信息
     * @param user
     */
    private static void writer(User user) {

        // 创建输出流,
        OutputStream outputStream = null;
        // 创建文件写入流对象
        ObjectOutputStream objectOutputStream = null;
        try {
            outputStream = new FileOutputStream(AddressUtils.getAddress(6),true);
            objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (objectOutputStream != null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
