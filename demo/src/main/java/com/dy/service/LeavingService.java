package com.dy.service;

import com.dy.entity.Leaving;
import com.dy.mapper.LeavingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LeavingService {
    @Autowired
    LeavingMapper leavingMapper;
    //提交
    public int submitLeaving(String name, String desc, String date) {
        return leavingMapper.submitLeaving(name, desc, date);
    }
    //获取
    public List<Leaving> getLeaving() {
        return leavingMapper.getLeaving();
    }

    //删除
    public int delLeaving(int id) {
        return leavingMapper.delLeaving(id);
    }

}
