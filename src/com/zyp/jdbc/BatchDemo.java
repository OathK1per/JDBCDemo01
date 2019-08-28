package com.zyp.jdbc;

import java.sql.*;

/**
 * 批处理：使用Statemente而不是preparedStatement因为不用预处理，不会限制大小
 * 使用addBatch()添加，使用executeBatch()执行
 * 可以把batch当做一个事务处理
 */
public class BatchDemo {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stat = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=UTC",
                    "root", "PasswordOfRoot");
            stat = conn.createStatement();
            long start = System.currentTimeMillis();

            conn.setAutoCommit(false);
            for (int i = 1; i < 20000; i++) {
                String name = "User" + i;
                stat.addBatch("insert into batch values (default, '" + name + "')");
            }
            stat.executeBatch();
            conn.commit();
            long end = System.currentTimeMillis();
            System.out.println(end - start);
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
