package com.zyp.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static Properties pros = new Properties();
    static {
        try {
            pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName(pros.getProperty("MySql.Driver"));
            Connection conn = DriverManager.getConnection(pros.getProperty("MySql.Url"), pros.getProperty("MySql.User"),
                    pros.getProperty("MySql.Password"));
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet res, PreparedStatement prep, Connection conn) {
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
