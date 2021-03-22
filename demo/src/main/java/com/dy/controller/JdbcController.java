package com.dy.controller;

import com.dy.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Author: ZT
 * Date: 2020/5/13 13:17
 * Version 1.0
 */
@RestController
@RequestMapping("/jdbc")
public class JdbcController {
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 全查
     * @return
     */
    @RequestMapping("/userAll")
    public List<User> user() {
        String sql = "select * from user";
        List<User> list = jdbcTemplate.query(sql, new RowMapper<User>() {
            User user = null;
            @Override
            public User mapRow(ResultSet set, int i) throws SQLException {
                user = new User();
                user.setId(set.getInt(1));
                user.setUser(set.getString(2));
                user.setPassword(set.getString(3));
                user.setEmail(set.getString(4));
                return user;
            }
        });
        return list;
    }

    /**
     * 插入
     * @param user
     * @param password
     * @param email
     * @return
     */
    @RequestMapping("/add")
    public int add(String user, String password, String email) {
        String sql = "insert into user values(id, ?, ?, ?) ";
        int flag = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, user);
                stmt.setString(2, password);
                stmt.setString(3, email);
            }
        });
        return flag;
    }

    /**
     * 删除
     * @param id 删除条件
     * @return
     */
    @RequestMapping("/del")
    public int del(int id) {
        String sql = "delete from user where id = ?";
        int flag = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
            }
        });
        return flag;
    }

    /**
     * 更新
     * @param id
     * @param user
     * @param password
     * @param email
     * @return
     */
    @RequestMapping("/update")
    public int update(int id, String user, String password, String email) {
        String sql = "update user set user = ?, password = ?, email = ? where id = ?";
        int flag = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, user);
                stmt.setString(2, password);
                stmt.setString(3, email);
                stmt.setInt(4, id);
            }
        });
        return flag;
    }
}
