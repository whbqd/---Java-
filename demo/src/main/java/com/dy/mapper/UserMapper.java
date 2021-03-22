package com.dy.mapper;

import com.dy.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    /**
     * 登录
     * @param user
     * @param password
     * @return
     */
    User login(String user, String password);

    /**
     * 注册
     * @param user
     * @param password
     * @param email
     * @return
     */
    Integer register(String user, String password, String email, String date);
    /**
     *查询用户名是否重复
     * @param user
     * @return
     */
    User queryByUser(String user);

    /**
     * 判断用户名邮箱是否正确
     * @param user
     * @param email
     * @return
     */
    User UserIsPwd(String user, String email);

    /**
     * 修改密码
     * @param user
     * @param password
     * @return
     */
    Integer updatePwd(String user, String password);

    /**
     * 全查
     * @return
     */
    List<User> queryAll();

    /**
     * 删除
     * @param id
     * @return
     */
    Integer del(Integer id);

    /**
     * 根据id查询User
     * @param id
     * @return
     */
    User queryById(Integer id);

    /**
     * 修改User
     * @param user
     * @param password
     * @param email
     * @return
     */
    Integer updateUser(String user, String password, String email, Integer id);

    /**
     * 根据id 查询user
     * @param id
     * @return
     */
    User queryIdByUser(Integer id);

    //记录用户登录的信息
    @Insert("insert into loginRecord values(id,#{user_id},#{date},#{user})")
    Integer loginRecord(@Param("user_id") Integer user_id,@Param("date") String date,@Param("user") String user);
}
