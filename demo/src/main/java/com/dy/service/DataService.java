package com.dy.service;

import com.dy.entity.LoginRecord;
import com.dy.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    @Autowired
    DataMapper dataMapper;
    public List<LoginRecord> loginRecords() {
        return dataMapper.loginRecord();
    }
}
