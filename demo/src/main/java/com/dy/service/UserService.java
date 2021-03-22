package com.dy.service;

import com.dy.entity.User;
import com.dy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    @Autowired
    UserMapper userMapper;

    /**
     * 登录
     * @param user
     * @param password
     * @return
     */
    public User login(String user, String password) {
        return userMapper.login(user, password);
    }

    /**
     * 注册
     * @param user
     * @param password
     * @param email
     * @return
     */
    public Integer register(String user, String password, String email, String date) {
        return userMapper.register(user, password, email, date);
    }

    /**
     * 判断用户名是否重复
     * @param user
     * @return
     */
    public User queryByUser(String user) {
        return userMapper.queryByUser(user);
    }

    /**
     * 判断用户名和邮箱是否正确
     * @param user
     * @param password
     * @return
     */
    public User UserIsPwd(String user, String password) {
        return userMapper.UserIsPwd(user, password);
    }

    /**
     * 修改密码
     * @param password
     * @return
     */
    public Integer updatePwd(String user, String password) {
        return userMapper.updatePwd(user, password);
    }

    /**
     * 全查
     * @return
     */
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public Integer del(Integer id) {
        return userMapper.del(id);
    }

    /**
     * 通过id查询user
     * @param id
     * @return
     */
    public User queryById(Integer id) {
        return userMapper.queryById(id);
    }

    /**
     * 修改用户信息
     * @param user
     * @param password
     * @param email
     * @param id
     * @return
     */
    public Integer updateUser(String user, String password, String email, Integer id) {
        return userMapper.updateUser(user, password, email, id);
    }

    /**
     * 通过id查询用户信息
     * @param id
     * @return
     */
    public User queryIdByUser(Integer id) {
        return userMapper.queryIdByUser(id);
    }

    public Integer loginRecord(Integer user_id, String date, String user) {
        return userMapper.loginRecord(user_id, date, user);
    }
}
