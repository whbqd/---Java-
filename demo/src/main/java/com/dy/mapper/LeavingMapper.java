package com.dy.mapper;

import com.dy.entity.Leaving;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeavingMapper {
    //提交
    @Insert("insert into leavingMsg values(id,#{name},#{desc},#{date})")
    int submitLeaving(@Param("name") String name, @Param("desc") String desc, @Param("date") String date);
    //获取
    @Select("select * from leavingMsg")
    List<Leaving> getLeaving();
    //删除
    @Delete("delete from leavingMsg where id = #{id}")
    int delLeaving(@Param("id") int id);
}
