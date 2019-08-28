package com.zyp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 测试PreparedStatement
 * ?占位符，可以使用setObject为所有数据进行赋值
 */
public class PrepStatDemo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=UTC",
                    "root", "PasswordOfRoot");
            prep = conn.prepareStatement("insert into user values (default,?,?,now(),now())");
            prep.setString(1, "ddd");
            prep.setInt(2, 26);
            int update = prep.executeUpdate();
            System.out.println(update);
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
