package com.zyp.jdbc;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 测试对时间的查询
 * 使用DateFormat或者Calendar指定一个时间范围
 * 注意DateFormat里面不同大小写字母代表不同的含义，不能混淆着写
 */
public class QueryTimeDemo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet res = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=UTC",
                    "root", "PasswordOfRoot");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String start = "2019-08-21 09:30:00";
            String end = "2019-08-25 18:00:00";
            long startTime = dateFormat.parse(start).getTime();
            Timestamp startStamp = new Timestamp(startTime);
            long endTime = dateFormat.parse(end).getTime();
            Timestamp endStamp = new Timestamp(endTime);
            prep = conn.prepareStatement("select * from time where update_time > ? and update_time < ? order by update_time desc;");
            prep.setObject(1, startStamp);
            prep.setObject(2, endStamp);
            res = prep.executeQuery();
            while (res.next()) {
                System.out.println(res.getObject("name") + "-->" + res.getObject("update_time"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (prep != null) {
                    prep.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
