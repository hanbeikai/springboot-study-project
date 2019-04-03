package com.beikai.springboottestdemo.IO;

import java.io.*;

/**
 * @ClassName Test04AboutPrintStream
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/3 20:49
 * @Version 1.0
 *  printStream 流的使用 打印流
 *
 **/
public class Test04AboutPrintStream {

    public static void main(String[] args) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("E:\\test\\abc5.java",true);

            PrintStream printStream = new PrintStream(outputStream);
            // 打印到文件中
            printStream.print("打印1");
            printStream.println("打印2");
            printStream.print("打印1");
            // 打印在屏幕上  System.out 系统默认输出设备是显示器
            System.out.println("hello world!");
            // 修改 System.out 打印方向  到流中
            System.setOut(printStream);
            // 打印在文件中
            System.out.println("这一行答应在文件中....");

            // 把异常信息打印在文件中
            try (
                    FileInputStream fileInputStream = new FileInputStream("dddd");
                    ){

            } catch (Exception e) {
                // 把异常信息打印在打印流中
                e.printStackTrace(printStream);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
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
