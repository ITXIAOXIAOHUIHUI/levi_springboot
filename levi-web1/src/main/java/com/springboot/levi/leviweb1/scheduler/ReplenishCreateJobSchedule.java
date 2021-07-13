package com.springboot.levi.leviweb1.scheduler;

import com.springboot.levi.leviweb1.runnable.ReplenishCreateJobRunable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jianghaihui
 * @date 2021/6/15 12:00
 * 自动生成入库单
 * 1、轮询是否有大于等待点的任务
 * 2、生成任务下发
 * 3、
 */
@Component
@Slf4j
public class ReplenishCreateJobSchedule implements Runnable {

    @Autowired(required = false)
    @Qualifier("QP_REPLENISH_JOB_CREATE_EXECUTOR")
    private TaskExecutor jobCreateExecutor;

    @Resource
    private ReplenishCreateJobRunable replenishCreateJobRunable;

    @Override
    public void run() {
        try {
            while (true) {
                replenishCreateJobRunable.run();
                Thread.sleep(2000L);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            jobCreateExecutor.execute(this);
        }
    }
}
