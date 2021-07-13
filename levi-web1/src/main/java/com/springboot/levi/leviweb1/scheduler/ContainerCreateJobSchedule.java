package com.springboot.levi.leviweb1.scheduler;

import com.springboot.levi.leviweb1.runnable.ContainerCreateJobRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jianghaihui
 * @date 2021/6/15 16:27
 */
@Component
@Slf4j
public class ContainerCreateJobSchedule implements Runnable {
    @Autowired(required = false)
    @Qualifier("QP_CONTAINER_JOB_CREATE_EXECUTOR")
    TaskExecutor qpContainerJobCreateExecutor;

    @Resource
    ContainerCreateJobRunnable containerCreateJobRunnable;

    @Override
    public void run() {
        try {
            while (true) {
                containerCreateJobRunnable.run();
                Thread.sleep(2000L);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            qpContainerJobCreateExecutor.execute(this);
        }
    }
}
