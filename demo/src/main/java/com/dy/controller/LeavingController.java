package com.dy.controller;

import com.dy.entity.Leaving;
import com.dy.packageEntity.Result;
import com.dy.service.LeavingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user/leaving")
public class LeavingController {
    @Autowired
    private LeavingService leavingService;
    //提交
    @PostMapping("/view/submitLeaving")
    public Result submitLeaving(@RequestParam String name, @RequestParam String desc) {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        System.out.println(ft.format(dNow));
        int flag = leavingService.submitLeaving(name, desc, ft.format(dNow));
        if(flag == 1) {
            return new Result(null,"发表成功", 100);
        }
        return new Result(null, "发表失败", 104);
    }
    //获取
    @PostMapping("/view/getLeaving")
    public Result getLeaving(@RequestParam Integer pageNow, @RequestParam Integer pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        List<Leaving> list = leavingService.getLeaving();
        PageInfo<Leaving> info = new PageInfo<>(list);
        return new Result(info, "获取成功成功", 100);
    }
    //删除
    @PostMapping("/view/delLeaving")
    public Result delLeaving(@RequestParam Integer id) {
        int flag = leavingService.delLeaving(id);
        if(flag == 1) {
            return new Result(null, "删除成功", 100);
        }
        return new Result(null, "删除失败", 104);
    }

}
