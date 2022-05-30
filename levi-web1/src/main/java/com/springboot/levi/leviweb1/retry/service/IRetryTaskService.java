package com.springboot.levi.leviweb1.retry.service;

import com.springboot.levi.leviweb1.retry.eums.RetryTaskType;

import java.util.concurrent.TimeUnit;

/**
 * @author jianghaihui
 * @date 2021/7/4 11:05
 */
public interface IRetryTaskService {

    boolean  scheduleWithFixedDelay(long interval , TimeUnit unit, RetryTaskType type,String id,int maxRetries);
}
