package com.zyp.jdbc;

import java.io.*;
import java.sql.*;

/**
 * 测试BLOB二进制大对象的使用
 * 如何从文件中传入数据，如何从数据库中传出数据并放入一个文件中，注意流的关闭
 */
public class BLOBDemo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet res = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=UTC",
                    "root", "PasswordOfRoot");

//            is = new FileInputStream("src/resources/flower.jpg");
//            prep = conn.prepareStatement("insert into info (id, name, image) values (default,?,?)");
//            prep.setObject(1, "info1");
//            prep.setBlob(2, is);
//            prep.executeUpdate();

            prep = conn.prepareStatement("select * from info where id = ?;");
            prep.setObject(1, 7);
            res = prep.executeQuery();
            if (res.next()) {
                Blob blob = res.getBlob("image");
                is = blob.getBinaryStream();
            }
            int index = -1;
            os = new FileOutputStream("src/resources/copy.jpg");
            while ((index = is.read()) != -1) {
                os.write(index);
            }
            os.flush();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
