package com.zyp.jdbc;

import java.sql.*;

/**
 * 测试ResultSet的用法
 * 游标从0开始，next则往下走1，直到后面没有数据
 * 关闭从里到外
 */
public class ResSetDemo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet res = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=UTC",
                    "root", "PasswordOfRoot");
            prep = conn.prepareStatement("select * from user where id > ?");
            prep.setObject(1, 1);
            ResultSet set = prep.executeQuery();
            while (set.next()) {
                System.out.println(set.getObject("name"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
