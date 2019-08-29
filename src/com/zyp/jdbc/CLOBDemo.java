package com.zyp.jdbc;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * 测试CLOB文本大对象的使用
 * 可以直接使用Reader从文本中读数据，也可以用byteArray读传输的String数据
 */
public class CLOBDemo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=UTC",
                    "root", "PasswordOfRoot");

            String str = "ceeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeb";
            prep = conn.prepareStatement("insert into info (id,name,info) values (default,?,?)");
            prep.setObject(1, "info1");
            prep.setClob(2, new BufferedReader(new InputStreamReader(new ByteArrayInputStream(str.getBytes()))));
            prep.executeUpdate();
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
