package com.zyp.jdbc;

import java.sql.*;

/**
 * 测试事务的执行
 */
public class TransDemo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep2 = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=UTC",
                    "root", "PasswordOfRoot");
            conn.setAutoCommit(false);

            String name = "ee";
            int age = 27;
            prep1 = conn.prepareStatement("insert into user values (default,?,?,now(),now())");
            prep1.setObject(1, name);
            prep1.setObject(2, age);
            prep1.execute();
            System.out.println("插入一条记录");

            String name2 = "fffff";
            prep2 = conn.prepareStatement("insert into user values (default,?,?,now(),now())");
            prep2.setObject(1, name);
            prep2.execute();
            System.out.println("插入一条记录");

            conn.commit();
        } catch (ClassNotFoundException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (prep2 != null) {
                    prep2.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (prep1 != null) {
                    prep1.close();
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
