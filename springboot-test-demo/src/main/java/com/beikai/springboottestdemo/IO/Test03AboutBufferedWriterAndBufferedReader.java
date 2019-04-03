package com.beikai.springboottestdemo.IO;

import java.io.*;

/**
 * @ClassName Test03AboutBufferedWriterAndBufferedReader
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/2 22:43
 * @Version 1.0
 *
 *  通过缓冲流读取数据
 *
 *  程序通过 FileReader 从文件中读取数据,存进BufferedReader缓冲流中,这个缓冲流对象的大小是 8092 ,然后程序从缓冲流中读取
 *  程序把从BufferedReader中读取的数据存进BufferedWriter缓冲流中,然后通过 FileWriter 写进文件中
 *
 *      注意:
 *          BufferedWriter 中的数据是直接写进文件中的吗?
 *          不是,三种情况下写入:
 *          1. 缓冲满了
 *          2. 调用close()方法
 *          3. 调用 flush()方法
 *
 **/
public class Test03AboutBufferedWriterAndBufferedReader {

    public static void main(String[] args) {
        String fromPath = "D:\\Windows10\\DevelopmentTools\\IntelliJ_IDEA\\WorkSpaceAboutGitHab" +
                "\\springboot-study-project\\springboot-test-demo\\src\\main\\java\\com\\beikai\\springboottestdemo\\IO\\Test01AboutFileWriterAndFileReader.java";
        String targetPath = "E:\\test\\abc4.java";
        String targetPath2 = "E:\\test\\abc5.java";
        //reader(fromPath,targetPath);
        //writer(fromPath,targetPath);

        // 键盘输入写入到文件中
        keyInput(fromPath,targetPath2);
    }

    /**
     * 键盘录入写入文件中
     * @param fromPath      源文件路径
     * @param targetPath2    目标文件路径
     */
    private static void keyInput(String fromPath, String targetPath2) {
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        try {
            // 创建写入缓冲流对象
            bufferedWriter = new BufferedWriter(new FileWriter(targetPath2,true));
            // 创建读取缓冲流对象   system.in 为键盘录入
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            // 读取每一行
            String line = bufferedReader.readLine();

            // 循环读取
            while (line.length() > 0){
                // 写入文件中
                bufferedWriter.write(line);
                // 换行
                bufferedWriter.newLine();
                // 在读取一行
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //关闭
    }

    /**
     * 通过缓冲流写入
     * @param fromPath      源文件路径
     * @param targetPath    目标文件路径
     */
    private static void writer(String fromPath, String targetPath) {
        Writer writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            // 声明写入流  第二个参数  是否追加
            writer = new FileWriter(targetPath,true);
            // 创建缓冲流对象
            bufferedWriter = new BufferedWriter(writer);

            // 写入数据
            bufferedWriter.write("测试赛的点点滴滴多多大多多多多多");

            // 情况缓冲区 把数据保存到文件中
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    /**
     * 通过缓冲流读取
     * @param fromPath      源文件路径
     * @param targetPath    目标文件路径
     */
    private static void reader(String fromPath, String targetPath) {
        // 声明对象
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            // 创建文件读取对象
            reader = new FileReader(fromPath);
            // 创建缓冲读取对象
            bufferedReader = new BufferedReader(reader);
            // 每次读取一行
            String line = bufferedReader.readLine();
            // 循环读取
            while (line != null ){
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
