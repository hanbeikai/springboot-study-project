package com.beikai.springbootmybatisplusdemo.common.constant;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2019-09-01
 * Time: 08:57
 * Description: No Description
 */
public class DatabaseConstant {

        /**
         * 读表
         */
        public static final String writer_url = "jdbc:mysql://172.16.195.130:3306/mytest?useUnicode=true&useSSL=false&characterEncoding=utf8";
        public static final String writer_DriverName= "com.mysql.jdbc.Driver";
        public static final String writer_Username = "root";
        public static final String writer_Password = "root";

        /**
         * 写表
         */
        public static final String read_url = "jdbc:mysql://172.16.195.133:3306/mytest?useUnicode=true&useSSL=false&characterEncoding=utf8";
        public static final String read_DriverName= "com.mysql.jdbc.Driver";
        public static final String read_Username = "root";
        public static final String read_Password = "root";


}
