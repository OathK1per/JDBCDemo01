package com.zyp.jdbc;

import java.sql.*;
import java.util.Random;

/**
 * 测试插入随机时间
 * 随机时间生成，使用Date，Time和TimeStamp
 * 对时间数据多用long值
 */
public class RandTimeDemo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=UTC",
                    "root", "PasswordOfRoot");

            for (int i = 1; i < 1000; i++) {
                String name = "Time" + i;
                Random random = new Random();
                long interval = 100000000 + random.nextInt(1000000000);

                Date date = new Date(System.currentTimeMillis() - interval);
                Timestamp stamp = new Timestamp(System.currentTimeMillis() - interval);
                prep = conn.prepareStatement("insert into time values (default,?,?,?);");
                prep.setObject(1, name);
                prep.setObject(2, date);
                prep.setObject(3, stamp);
                prep.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
