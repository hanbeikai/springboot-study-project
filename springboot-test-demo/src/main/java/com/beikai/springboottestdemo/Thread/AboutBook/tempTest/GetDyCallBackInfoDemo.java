package com.beikai.springboottestdemo.Thread.AboutBook.tempTest;/**
 * Created with IntelliJ IDEA.
 * User: beikai
 * Date: 2020/5/27
 * Time: 14:15
 * Description:
 */

import java.net.URLEncoder;
import java.sql.*;

/**
 * @author hbk
 * @ClassName:
 * @Description:
 * @date 2020/5/27 14:15
 */
public class GetDyCallBackInfoDemo {

    public static void main(String[] args) {
        Connection connection = SingleDemo12.getConnection();

        try {

            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = connection.createStatement();
            //要执行的SQL语句
            String sql = "select distinct ad_id,creative_id,creative_type,click_id from cus_user_relation_dy where activity_num = 4 and create_time >= '2020-05-21 00:00:00' and create_time < '2020-05-23 00:00:00'";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");

            String ad_id = null;
            String creative_id = null;
            String creative_type = null;
            String click_id = null;

            while (rs.next()) {
                //获取stuname这列数据
                ad_id = rs.getString("ad_id");
                //获取stuid这列数据
                creative_id = rs.getString("creative_id");
                creative_type = rs.getString("creative_type");
                click_id = rs.getString("click_id");

                String url = "https://web.jiuweihabao.com/znhb/index.html" +
                        "?adid=" + ad_id +
                        "&clickid=" + click_id +
                        "&creativeid=" + creative_id +
                        "&creativetype=" + creative_type +
                        "#/pIndex?state=douyin&activityNum=4";

                String backUrl = "https://ad.toutiao.com/track/activate/?link=" + URLEncoder.encode(url, "UTF-8")
                        + "&source=JW" + "&event_type=" +  3;

                System.out.println(backUrl);

            }
            rs.close();
            connection.close();
        } catch (Exception e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }// TODO: handle exception
        finally {
            System.out.println("数据库数据成功获取！！");
        }
    }

}