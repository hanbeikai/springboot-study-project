package com.beikai.springboottestdemo.designPattern.proxyPattern.vmProxyPattern.proxy;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/12
 * Time: 12:08 下午
 * Description: 图片代理类
 */
public class ImageProxy implements Icon {

    // swing 显示图片的 对象
    ImageIcon imageIcon;
    // 图片地址
    URL imgUrl;
    // 用于下载图片的线程
    Thread retrievalThread;
    // 判断是否已经加载了图片
    boolean retrieving = false;

    // 构造的时候传入图片地址
    public ImageProxy(URL imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {

        // 判断图片是否已经加载成功,如果如果加载成功,返回加载成功后的图片
        if (null != imageIcon) {
            imageIcon.paintIcon(c,g,x,y);
        }else {
            // 如果没有加载成功,打印 图片正在加载
            g.drawString("图片正在加载 ... ",x+300,y+190);
            // 调用一个线程加载,通过 retrieving 判断是否已经执行过加载的命令
            if (!retrieving) {
                retrieving = true;
                System.out.println("图片开始加载 ... ");
                // 开启一个线程异步加载
                retrievalThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        imageIcon = new ImageIcon(imgUrl,"cd start ... ");
                        // 加载完成后,重新刷新 swing 页面
                        c.repaint();
                    }
                });

                // 执行线程
                retrievalThread.start();
            }
        }

    }

    @Override
    public int getIconWidth() {

        if (null == imageIcon) {
            // 判断是否已经加载成功,如果未成功,返回默认宽度
            return 800;
        }
        return imageIcon.getIconWidth();
    }

    @Override
    public int getIconHeight() {
        if (null == imageIcon) {
            return 600;
        }
        return imageIcon.getIconHeight();
    }
}
