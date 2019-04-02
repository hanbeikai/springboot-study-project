package com.beikai.springboottestdemo.IO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName Test01
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/2 21:41
 * @Version 1.0
 * 测试 字符输入流 和 字符输出流
 *  FileReader  和  FileWriter
 *
 *   缺点:
 *      filereader / fileWriter 只能写与当前环境编码兼容的文本文件
 *      如果文本文件与当前环境编码不兼容,使用InputStreamReader/OutputStreamWriter  转换流
 **/
public class Test01 {
    public static void main(String[] args) {

        String fromPath = "D:\\Windows10\\DevelopmentTools\\IntelliJ_IDEA\\WorkSpaceAboutGitHab" +
                "\\springboot-study-project\\springboot-test-demo\\src\\main\\java\\com\\beikai\\springboottestdemo\\IO\\Test01.java";
        String targetPath = "E:\\test\\abc.java";
        String targetPath2 = "E:\\test\\abc2.java";

        copyFileOneChar(fromPath,targetPath);
        copyFileOneArray(fromPath,targetPath2);

    }

    /**
     * 拷贝文件
     * 文本文件复制,一次复制一个字符,异常处理,会自动关闭流
     * @param fromPath   源文件路径
     * @param targetPath 目标文件路径
     */
    public static void copyFileOneChar(String fromPath, String targetPath) {

        /**
         * 流对象放进 try(){} 的()中, 创建的资源会自动释放
         */
        try(// 创建字符读取流
            FileReader fileReader = new FileReader(fromPath);
            // 创建字符写入流
            FileWriter fileWriter = new FileWriter(targetPath);
            ) {
            // 设置每次读取的字符
            int cc = fileReader.read();
            // 如果不为 -1 说明还没读完
            while (cc != -1){
                // 调用写入流写入字符
                fileWriter.write(cc);
                // 重新读取
                cc = fileReader.read();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  拷贝文件,一次拷贝一个数组
     * @param fromPath  源文件目录
     * @param targetPath  目标文件路径
     */
    public static void copyFileOneArray(String fromPath, String targetPath){

        // 声明 字符读取流和字符写入流对象
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {

            fileReader = new FileReader(fromPath);
            fileWriter = new FileWriter(targetPath);
            // 创建数组,用于存放读取的数据
            char[] c = new char[1024];
            // 获取当前数组中读取的数量
            int len = fileReader.read(c);

            while (len != -1){
                // 写入流 根据数组写入数据  第一个参数是 数组,第二个参数是 读取的起始位置, 第三个参数是 读取的终止位置
                fileWriter.write(c,0,len);
                // 再次读取
                len = fileReader.read(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 最终 关闭流
            if (fileReader!= null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
