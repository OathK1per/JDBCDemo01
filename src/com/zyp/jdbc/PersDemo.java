package com.zyp.jdbc;

import java.sql.*;

/**
 * 测试数据的持久化
 * 使用javabean来封装查询的数据
 * 多条数据可以使用list<javabean>来装
 */
public class PersDemo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet res = null;
        try {
            conn = JDBCUtils.getConnection();
            prep = conn.prepareStatement("select * from user where id = ?");
            prep.setObject(1, 4);
            res = prep.executeQuery();
            while (res.next()) {
                User user = new User();
                user.setId(res.getInt("id"));
                user.setName(res.getString("name"));
                user.setAge(res.getInt("age"));
                user.setCreateDay(res.getDate("create_day"));
                user.setUpdateTime(res.getTimestamp("update_time"));
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(res, prep, conn);
        }
    }
}

class User {
    private int id;
    private String name;
    private int age;
    private Date createDay;
    private Timestamp updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", createDay=" + createDay +
                ", updateTime=" + updateTime +
                '}';
    }
}