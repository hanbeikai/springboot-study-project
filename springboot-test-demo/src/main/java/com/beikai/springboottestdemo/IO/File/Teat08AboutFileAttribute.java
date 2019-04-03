package com.beikai.springboottestdemo.IO.File;

import com.beikai.springboottestdemo.IO.util.AddressUtils;

import java.io.File;

/**
 * @ClassName Teat08AboutFileAttribute
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/3 22:13
 * @Version 1.0
 *  通过 file 获取文件属性
 *
 *      文件是否存在 ---> true
 *      文件绝对路径 ---> E:\test\abc\hehe.mp4
 *      文件路径 ---> E:\test\abc\hehe.mp4
 *      文件父路径 ---> E:\test\abc
 *      文件名 ---> hehe.mp4
 *      文件大小 ---> 23027559
 *      文件是否是文件 ---> true
 *      文件路径是否是绝对路径 ---> true
 *      文件最后修改时间 ---> 1554301030490
 **/
public class Teat08AboutFileAttribute {

    public static void main(String[] args) {
        // 获取文件属性
        File file = new File(AddressUtils.getFilePath(0)+"\\hehe.mp4");

        System.out.println("文件是否存在 ---> "+file.exists());
        System.out.println("文件绝对路径 ---> "+file.getAbsolutePath());
        System.out.println("文件路径 ---> "+file.getPath());
        System.out.println("文件父路径 ---> "+file.getParent());
        System.out.println("文件名 ---> "+file.getName());
        System.out.println("文件大小 ---> "+file.length());
        System.out.println("文件是否是文件 ---> "+file.isFile());
        System.out.println("文件路径是否是绝对路径 ---> "+file.isAbsolute());
        System.out.println("文件最后修改时间 ---> "+file.lastModified());
    }
}
