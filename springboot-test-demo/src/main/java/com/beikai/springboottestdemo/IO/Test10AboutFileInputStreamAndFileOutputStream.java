package com.beikai.springboottestdemo.IO;

import com.beikai.springboottestdemo.IO.util.AddressUtils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ClassName Test10AboutFileInputStreamAndFileOutputStream
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/3 22:54
 * @Version 1.0
 *
 *  使用字节流读取数据
 **/
public class Test10AboutFileInputStreamAndFileOutputStream {

    public static void main(String[] args) {

        readerOneChar(AddressUtils.getAddress(5));

        readerOneArray(AddressUtils.getAddress(5));
    }

    /**
     * 读取数据,一次读取一个数组
     * @param address
     */
    private static void readerOneArray(String address) {

        FileInputStream fileInputStream = null;
        try {
            // 创建流通道
            fileInputStream = new FileInputStream(address);
            // 读取内容  read() 方法每次读取一个字节数组  然后把读到的字节数组长度返回(以数字的形式),如果读到的是最后 返回 -1

            byte[] e = new byte[1024];

            int len = fileInputStream.read(e);
            while (len != -1){
                // 打印读取的数据  由于是整形的形式(对应的是字符的码值),需要强制转换的字符的形式
                System.out.print(new String(e,0,len));
                len = fileInputStream.read(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null){
                // 关闭流
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取数据 一次读取一个字符
     * @param address
     */
    private static void readerOneChar(String address) {
        FileInputStream fileInputStream = null;
        try {
            // 创建流通道
            fileInputStream = new FileInputStream(AddressUtils.getAddress(5));
            // 读取内容  read() 方法每次读取一个字节  然后把读到的字节返回(以数字的形式),如果读到的是最后 返回 -1
            int len = fileInputStream.read();
            while (len != -1){
                // 打印读取的数据  由于是整形的形式(对应的是字符的码值),需要强制转换的字符的形式
                System.out.print((char)len);
                len = fileInputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null){
                // 关闭流
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
