package com.zyp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 测试与数据库之间的连接，连接速度慢，一般在工程中使用连接池来辅助数据库连接
 */
public class ConnDemo {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            long start = System.currentTimeMillis();
            Class.forName("com.mysql.cj.jdbc.Driver");
            long mid = System.currentTimeMillis();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=UTC",
                    "root", "PasswordOfRoot");
            long end = System.currentTimeMillis();
            System.out.println(mid - start);
            System.out.println(end - mid);
            System.out.println(conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
