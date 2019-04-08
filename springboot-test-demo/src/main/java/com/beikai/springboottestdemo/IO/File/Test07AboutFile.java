package com.beikai.springboottestdemo.IO.File;

import com.beikai.springboottestdemo.IO.util.AddressUtils;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName Test07AboutFile
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/3 21:56
 * @Version 1.0
 *
 *  file 是对文件级别的操作, 流是对文件内容的操作
 **/
public class Test07AboutFile {

    public static void main(String[] args) {
        // 根据地址,创建文件夹
        File file = new File(AddressUtils.getFilePath(0));
        // 创建文件夹
        file.mkdir();

        // 根据文件地址,创建子文件夹
        File file1 = new File(AddressUtils.getFilePath(0)+"\\StorageTest");
        file1.mkdir();

        // 根据文件路径,以及文件名,创建文件  第一个参数是路径,第二个参数是文件名
        File file2 = new File(AddressUtils.getFilePath(0),"abc.txt");
        try {
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 同上
        File file3 = new File(file1,"abc2.txt");
        try {
            file3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
