package com.springboot.levi.leviweb1.lock.impl.distributed.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.IOException;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-06-15 17:11
 */
@Slf4j
@Configuration
public class RedissonConfig {
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private String redisPort;
    @Value("${redisson.connection.poolsize}")
    private int connectionPoolSize;
    @Value("${redisson.redis.database}")
    private int database;

    private Config getRedissonConfig() {
        String redisAddr = "redis://" + redisHost + ":" + redisPort;

        Config config = new Config();
        config.useSingleServer()
                .setAddress(redisAddr)
                .setConnectionPoolSize(connectionPoolSize)
                .setDatabase(database);

        return config;
    }

    @Bean(destroyMethod="shutdown")
    @DependsOn({"springBeanFactory"})
    public RedissonClient redisson() throws IOException {
        log.info("init redisson client...");
        RedissonClient redission = Redisson.create(this.getRedissonConfig());
        log.info("init redisson client end");
        return redission;
    }
}
