package com.beikai.springboottestdemo.designPattern.proxyPattern.vmProxyPattern.basic;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/12
 * Time: 12:24 下午
 * Description: 自定义图像组件
 */
public class ImageComponent extends JComponent {

    private Icon icon;

    public ImageComponent(Icon icon) {
        this.icon = icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    @Override
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        int x = (800 - w) / 2;
        int y = (600 - h) / 2;
        icon.paintIcon(this,graphics,x,y);

    }
}
