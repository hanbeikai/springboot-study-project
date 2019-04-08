package com.beikai.springboottestdemo.IO;

import java.io.*;

/**
 * @ClassName Test02AboutOutputStreamWriterAndInputStreamReader
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/2 22:15
 * @Version 1.0
 *
 * 使用转换流方式读写数据
 *  当文本编码格式与环境编码格式不同时使用
 *  OutputStreamWriter 和  OutputStream   以及   InputStreamReader  和   InputStream
 *  转化流采用适配器设计模式
 *
 *   设计模式 "
 *      别人总结的一套解决方案,并被大多数人接受
 *
 *    适配器 :
 *      不同之间转换的工具
 **/
public class Test02AboutOutputStreamWriterAndInputStreamReader {

    public static void main(String[] args) {

        String fromPath = "D:\\Windows10\\DevelopmentTools\\IntelliJ_IDEA\\WorkSpaceAboutGitHab" +
                "\\springboot-study-project\\springboot-StorageTest-demo\\src\\main\\java\\com\\beikai\\springboottestdemo\\IO\\Test01AboutFileWriterAndFileReader.java";
        String targetPath = "E:\\StorageTest\\abc3.java";

        reader(fromPath,targetPath);

        writer(fromPath,targetPath);
    }

    /**
     *  写入转换流
     * @param fromPath    源文件路径
     * @param targetPath   目标文件路径
     */
    private static void writer(String fromPath, String targetPath) {
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            // 创建一个字节写入流  第一个参数是 目标文件, 第二个参数是 是否叠加
            outputStream = new FileOutputStream(targetPath,true);
            // 创建一个转换流  第一个参数是 输入流对象  第二个参数是 目标文件编码格式
            outputStreamWriter = new OutputStreamWriter(outputStream,"utf-8");

            //写入
            outputStreamWriter.write("创建一个转换流  第一个参数是 输入流对象  第二个参数是 目标文件编码格式");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 读取转换流
     * @param fromPath    源文件路径
     * @param targetPath   目标文件路径
     */
    private static void reader(String fromPath, String targetPath) {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            // 创建一个字节读取流
            inputStream = new FileInputStream(fromPath);
            // 创建一个转换流  第一个参数是  字节读取流, 第二个参数是 源文件编码格式
            inputStreamReader = new InputStreamReader(inputStream,"utf-8");

            // 一次读取一个字节
            int cc = inputStreamReader.read();
            while (cc != -1){
                // 输出  也可以在这里写入
                System.out.print((char) cc);
                cc = inputStreamReader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }


}
