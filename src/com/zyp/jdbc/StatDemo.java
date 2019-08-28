package com.zyp.jdbc;

import java.sql.*;

/**
 * 测试statement接口，执行sql语句
 * 无法防止sql注入问题
 */
public class StatDemo {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stat = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=UTC",
                    "root", "PasswordOfRoot");
            stat = conn.createStatement();

            //从外部添加数据比较麻烦，没有预加载，速度较慢
//            String name = "aaa";
//            int age = 18;
//            stat.execute("insert into user (name, age) values ('" + name + "', " + age + ")");

            String age = "55 or 1 = 1";
            ResultSet resultSet = stat.executeQuery("select * from user where age = " + age);
            while(resultSet.next()) {
                System.out.println(resultSet.getObject("name"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
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
