package com.beikai.springboottestdemo.IO.File;

import com.beikai.springboottestdemo.IO.util.AddressUtils;

import java.io.File;

/**
 * @ClassName Test09AboutFolder
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/3 22:28
 * @Version 1.0
 *  对文件夹的操作
 **/
public class Test09AboutFolder {

    public static void main(String[] args) {

        getFolderName(AddressUtils.getFilePath(0));
        System.out.println("------------------------------");
        getFolderPath(AddressUtils.getFilePath(0));
        System.out.println("------------------------------");
        getFolderContent(AddressUtils.getFilePath(0));
    }

    /**
     * 获取文件夹内容
     * @param filePath
     */
    private static void getFolderContent(String filePath) {
        File file = new File(filePath);

        // 获取文件对象
        File[] files = file.listFiles();

        for (File file1 : files) {
            System.out.println(file1.getAbsolutePath());
            if (file1.isDirectory()){
                getFolderContent(file1.getAbsolutePath());
            }
        }
    }

    /**
     * 获取文件夹路径
     * @param filePath
     */
    private static void getFolderPath(String filePath) {
        File file = new File(filePath);
        // 获取路径经下文件对象
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1.getAbsolutePath());
        }
    }

    /**
     * 获取文件名
     * @param filePath
     */
    private static void getFolderName(String filePath) {
        File file = new File(filePath);

        // 获取文件列表信息
        String[] list = file.list();
        for (String s : list) {
            System.out.println(s);
        }
    }
}
