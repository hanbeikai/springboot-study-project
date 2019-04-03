package com.beikai.springboottestdemo.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @ClassName Test05AboutPrintWriter
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/3 21:01
 * @Version 1.0
 *
 *  PrintWriter 的使用方式
 *
 *   以字符为单位的打印流
 **/
public class Test05AboutPrintWriter {
    public static void main(String[] args) {
        // 创建字符打印流
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter("E:\\test\\abc6.java",true));

            try (
                    FileInputStream fileInputStream = new FileInputStream("dddd");
                    ){

            } catch (FileNotFoundException e) {
                // 打印到这个流中
                e.printStackTrace(printWriter);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (null != printWriter){
                printWriter.close();
            }
        }

    }
}
