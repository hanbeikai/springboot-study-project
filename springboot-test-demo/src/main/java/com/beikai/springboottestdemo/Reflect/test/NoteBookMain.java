package com.beikai.springboottestdemo.Reflect.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName NoteBookMain
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/5 19:14
 * @Version 1.0
 * 启动笔记本的方法
 **/
public class NoteBookMain {

    public static void main(String[] args) {
        FileReader fileReader = null;
        try {
            // 创建 笔记本对象
            NoteBookModel noteBookModel = new NoteBookModel();
            noteBookModel.run();

            // 读取配置文件中信息  NoteBookMain.class.getResource 获取的是 编译后的 class文件所在路径
            ///D:/Windows10/DevelopmentTools/IntelliJ_IDEA/WorkSpaceAboutGitHab/springboot-study-project/springboot-StorageTest-demo/target/classes/
            String path = NoteBookMain.class.getResource("/").getPath();
            int i = path.lastIndexOf("/target");
            String substring = path.substring(0, i);
            File file = new File(substring+"/src/main/resources/properties/usb.properties");
            // 判断文件是否存在 如果不存在,创建
            if (!file.exists()) {
                file.createNewFile();
            }
            // 读取文件内容
            fileReader = new FileReader(file);
            // 创建配置文件对象
            Properties properties = new Properties();
            properties.load(fileReader);
            // 遍历获取文件内容  配置文件命名规范  usb1,usb2...
            for (int x = 1; x <= properties.size(); x++) {
                // 读取配置文件内容
                String classname = properties.getProperty("usb" + x);
                // 获取字节码对象
                Class<?> aClass = Class.forName(classname);
                // 实例化对象
                USB usb = (USB) aClass.newInstance();
                // 添加usb
                noteBookModel.openUsb(usb);
                noteBookModel.closeUsb(usb);

            }

            // 关闭电脑
            noteBookModel.close();

        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
