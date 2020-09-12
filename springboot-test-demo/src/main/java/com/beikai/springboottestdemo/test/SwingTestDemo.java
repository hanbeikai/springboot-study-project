package com.beikai.springboottestdemo.test;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/10
 * Time: 9:49 上午
 * Description: 测试 swing 组件
 */
public class SwingTestDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowSwing();
        });
    }

    private static void createAndShowSwing() {
        // 设置默认外观
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 设置关闭
        JFrame jFrame = new JFrame("hello jFrame ... ");
        // 设置窗口大小
        jFrame.setSize(1200,900);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //添加标签
        JLabel jLabel = new JLabel("哈哈哈 ... ");
        jFrame.getContentPane().add(jLabel);

        // 创建面板
        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);

        addDiv(jPanel);


        // 显示窗口
        jFrame.pack();
        jFrame.setVisible(true);

    }

    private static void addDiv(JPanel panel) {

        // 自定义面板
        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("User:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        // 创建登录按钮
        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
    }
}
