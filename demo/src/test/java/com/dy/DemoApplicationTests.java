package com.dy;

import com.dy.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Test
    void contextLoads() {
    }
    @Test
    void textre() {
        for (int i = 0; i < 8; i++) {
            redisTemplate.boundListOps("list").rightPush(i);
        }
        System.out.println(redisTemplate.boundListOps("list").range(0, 8));
        
    }

}
