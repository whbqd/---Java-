package com.dy.controller;
import com.dy.packageEntity.*;
import com.dy.service.UserService;
import com.dy.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
//        System.out.println(user.getUser()+"  " + user.getPassword());
        User u = userService.login(user.getUser(), user.getPassword());
        if(u != null) {
            String token = UUID.randomUUID().toString().replaceAll("-","");


            //存入redis
            redisTemplate.opsForValue().set(token, u, Duration.ofMinutes(30L));
            //通过token查询user
            User loginUser = (User) redisTemplate.opsForValue().get(token);
            //获取id
            assert loginUser != null;
            int id = loginUser.getId();
            //获取日期
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
            userService.loginRecord(id, ft.format(dNow), u.getUser());
            return new Result(token, "登录成功", 100);
        }
        return new Result(null, "登录失败", 104);
    }

    /**
     * 退出登录 删除redis的token
     * @param request
     * @return
     */
    @RequestMapping("/view/logout")
    public Result logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        Boolean delete = redisTemplate.delete(token);
        if(delete) {
            return new Result(true, "退出成功", 100);
        }
        return new Result(null, "退出失败", 104);
    }
    /**
     * 通过token查询User信息
     * @param request
     * @return
     */

    @RequestMapping("/view/getUserOfLogin")
    public Result getUserOfLogin(HttpServletRequest request) {
//        获取token
        String token = request.getHeader("token");
        Object user = redisTemplate.opsForValue().get(token);
        if(user == null) {
            return new Result(null, "查询用户信息失败", 104);
        }
        return new Result(user, "查询用户信息成功", 100);
    }
    /**
     * 注册
     * @param user
     * @return
     */

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        System.out.println(user);
        //查询用户名是否重复
        User isRepeat = userService.queryByUser(user.getUser());
        //重复
        if (isRepeat != null) {
            return new Result(null, "用户名重复", 104);
        }
        //不重复,执行注册方法
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        Integer flag = userService.register(user.getUser(), user.getPassword(), user.getEmail(), ft.format(dNow));
        System.out.println(flag);
        isRepeat = userService.queryByUser(user.getUser());
        if (flag != 1) {
            return new Result(null, "注册失败", 104);
        }
        return new Result(isRepeat, "注册成功", 100);
    }

    /**
     * 判断用户名和邮箱是否正确
     * @param user
     * @param email
     * @return
     */

    @PostMapping("/checking")
    public Result checking(@RequestParam String user, @RequestParam String email) {
        User u = userService.UserIsPwd(user, email);
        if(u == null) {
            return new Result(null, "用户名或邮箱不正确", 104);
        }
        return new Result(u, "用户名邮箱正确", 100);
    }

    /**
     * 修改密码
     * @param user
     * @param password
     * @return
     */

    @PostMapping("/updatePwd")
    public Result updatePwd(@RequestParam String user, @RequestParam String password) {
        Integer flag = userService.updatePwd(user, password);
        if(flag != 1) {
            return new Result(null, "修改失败", 104);
        }
        return new Result(userService.queryByUser(user), "修改成功", 100);
    }

    /**
     * 全查
     * @return
     */

    @PostMapping("/view/queryAll")
    public Result queryAll(@RequestParam Integer pageNow, @RequestParam Integer pageSize) {
        System.out.println(pageNow+  "   " + pageSize);
        PageHelper.startPage(pageNow, pageSize);
        List<User> list = userService.queryAll();
        PageInfo<User> info = new PageInfo<>(list);
        return new Result(info, "全查成功", 100);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/view/del")
    public Result del(@RequestParam Integer id) {
        User user = userService.queryById(id);
        int flag = userService.del(id);
        if(flag != 1) {
            return new Result(null, "删除失败", 104);
        }
        return new Result(user, "删除成功", 100);
    }

    /**
     *修改用户信息
     * @param user
     * @param password
     * @param email
     * @param id
     * @return
     */
    @PostMapping("/view/updateUser")
    public Result updateUser(@RequestParam String user, @RequestParam String password, @RequestParam String email, @RequestParam Integer id) {
        Integer flag = userService.updateUser(user, password, email, id);
        if(flag != 1) {
            return new Result(null, "修改失败", 104);
        }
        return new Result(userService.queryById(id), "修改成功", 100);
    }

    /**
     * 通过id查询用户信息
     * @param id
     * @return
     */
    @PostMapping("/view/queryIdByUser")
    public Result queryIdByUser(@RequestParam Integer id) {
        User user = userService.queryIdByUser(id);
        if(user != null) {
            return new Result(user, "查询成功", 100);
        }
        return new Result(null, "查询失败", 104);
    }
}
