package com.springboot.levi.leviweb1.Thread.scheduler;

import com.springboot.levi.leviweb1.Thread.WorkMode;
import com.springboot.levi.leviweb1.Thread.runnable.ContainerCreateJobRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jianghaihui
 * @date 2021/6/15 16:27
 */
@Component
@Slf4j
@ConditionalOnProperty(value = "SI_ROLLER.enabled", havingValue = "true")
public class ContainerCreateJobSchedule implements Runnable, DisposableBean {
    @Autowired(required = false)
    @Qualifier("QP_CONTAINER_JOB_CREATE_EXECUTOR")
    TaskExecutor qpContainerJobCreateExecutor;

    @Autowired
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

    @Override
    public void destroy() throws Exception {

    }
}
