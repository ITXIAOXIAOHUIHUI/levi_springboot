package com.levi.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author jianghaihui
 * @date 2020/12/13 11:47
 */
@SpringBootTest
public class RedisSpringbootApplication {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads(){
        final RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
    }
}
