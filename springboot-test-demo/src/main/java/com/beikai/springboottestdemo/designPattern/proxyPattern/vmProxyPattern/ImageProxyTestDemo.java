package com.beikai.springboottestdemo.designPattern.proxyPattern.vmProxyPattern;

import com.beikai.springboottestdemo.designPattern.proxyPattern.vmProxyPattern.basic.ImageComponent;
import com.beikai.springboottestdemo.designPattern.proxyPattern.vmProxyPattern.proxy.ImageProxy;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/12
 * Time: 12:29 下午
 * Description: No Description
 */
public class ImageProxyTestDemo {

    ImageComponent imageComponent;
    JFrame jFrame = new JFrame("cd cover viewer");
    JMenuBar jMenuBar ;
    JMenu jMenu;
    Hashtable<String,String> hashtable = new Hashtable();

    public static void main(String[] args) throws MalformedURLException {
        ImageProxyTestDemo imageProxyTestDemo = new ImageProxyTestDemo();
    }

    // 构造
    public ImageProxyTestDemo() throws MalformedURLException {

        hashtable.put("image01","https://raw.githubusercontent.com/hanbeikai/my_pic_repository/master/image_repository/20200907170209.png");
        hashtable.put("image02","https://raw.githubusercontent.com/hanbeikai/my_pic_repository/master/image_repository/20200908175610.png");
        hashtable.put("image03","https://raw.githubusercontent.com/hanbeikai/my_pic_repository/master/image_repository/20200905092653.png");
        hashtable.put("image04","https://raw.githubusercontent.com/hanbeikai/my_pic_repository/master/image_repository/20200905092627.png");
        hashtable.put("image05","https://raw.githubusercontent.com/hanbeikai/my_pic_repository/master/image_repository/20200905092618.png");
        hashtable.put("image06","https://raw.githubusercontent.com/hanbeikai/my_pic_repository/master/image_repository/20200905092556.png");

        URL initImageUrl = new URL(hashtable.get("image01"));
        // 创建工具条
        jMenuBar = new JMenuBar();
        // 添加菜单
        jMenu = new JMenu("最爱的cd");
        // 菜单添加到 工具条上
        jMenuBar.add(jMenu);
        jFrame.setJMenuBar(jMenuBar);

        for (Enumeration<String> e = hashtable.keys(); e.hasMoreElements(); ) {

            String name = e.nextElement();
            JMenuItem jMenuItem = new JMenuItem(name);
            jMenu.add(jMenuItem);
            jMenuItem.addActionListener(new ActionListener() {
                @SneakyThrows
                @Override
                public void actionPerformed(ActionEvent e) {
                    imageComponent.setIcon(new ImageProxy(getCDUrl(e.getActionCommand())));
                    jFrame.repaint();
                }
            });

        }
        // 建立框架和菜单
        Icon icon = new ImageProxy(initImageUrl);
        imageComponent = new ImageComponent(icon);
        jFrame.getContentPane().add(imageComponent);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800,600);
        jFrame.setVisible(true);

    }

    URL getCDUrl(String name) throws MalformedURLException {
       return new URL(hashtable.get(name)) ;
    }
}
