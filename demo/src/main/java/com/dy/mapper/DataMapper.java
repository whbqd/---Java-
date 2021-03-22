package com.dy.mapper;

import com.dy.entity.LoginRecord;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DataMapper {
    @Select("select * from loginRecord")
    List<LoginRecord> loginRecord();
}
