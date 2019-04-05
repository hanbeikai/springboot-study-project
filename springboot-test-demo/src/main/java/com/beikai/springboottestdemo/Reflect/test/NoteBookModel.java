package com.beikai.springboottestdemo.Reflect.test;

/**
 * @ClassName NoteBookModel
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/5 19:13
 * @Version 1.0
 *  笔记本实体类
 **/
public class NoteBookModel {

    /**
     * 启动笔记本
     */
    public void run(){
        System.out.println("笔记本启动了...");
    }

    /**
     * 关闭笔记本
     */
    public void close(){
        System.out.println("笔记本关闭了...");
    }

    /**
     * 开启USB
     */
    public void openUsb(USB usb){
        if (usb != null){
            usb.start();
        }
    }

    /**
     * 关闭usb
     * @param usb
     */
    public void closeUsb(USB usb){
        if (usb != null){
            usb.close();
        }
    }

}
