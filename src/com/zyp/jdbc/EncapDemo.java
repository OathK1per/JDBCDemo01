package com.zyp.jdbc;

import java.sql.*;

/**
 * 测试封装JDBCUtils工具类
 * 封装连接信息和关闭系统
 * 在Utils类加载properties文件时，注意从resources开始而不是src开始
 */
public class EncapDemo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet res = null;
        try {
            conn = JDBCUtils.getConnection();
            prep = conn.prepareStatement("select * from user where id > ?");
            prep.setObject(1, 1);
            res = prep.executeQuery();
            while (res.next()) {
                System.out.println(res.getObject("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(res, prep, conn);
        }
    }
}
