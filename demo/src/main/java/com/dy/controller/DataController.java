package com.dy.controller;

import com.dy.packageEntity.Result;
import com.dy.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/data")
public class DataController {
    @Autowired
    DataService dataService;

    @PostMapping("/loginRecord")
    public Result loginRecord() {
        return new Result(dataService.loginRecords(),"登录记录查询成功",100);
    }
}
