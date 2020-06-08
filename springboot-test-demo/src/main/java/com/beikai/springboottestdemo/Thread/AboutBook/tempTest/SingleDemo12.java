package com.beikai.springboottestdemo.Thread.AboutBook.tempTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// 单例模式11 避免暴漏 枚举类
public class SingleDemo12 {
   public enum SingleDemo13 {
        connectionFactory;
        private Connection connection;
        private SingleDemo13() {
            try {
                System.out.println("调用了SingleDemo10构造！");
                String username = "jiuwei";
                String password = "ujJa4Sm*c48PX*kk";
                String url = "jdbc:mysql://rm-bp14250gj4n0n97f0zo.mysql.rds.aliyuncs.com:3306/ninetales?zeroDateTimeBehavior=convertToNull&characterEncoding=utf-8&jdbcCompliantTruncation=false&autoReconnect=true&useSSL=false";
                String driverName = "com.mysql.cj.jdbc.Driver";
                Class.forName(driverName);
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        public Connection getConnection(){
            return connection;
        }
    }

    public static Connection getConnection(){
       return SingleDemo13.connectionFactory.connection;
    }
}




