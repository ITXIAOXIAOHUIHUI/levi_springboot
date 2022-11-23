package com.springboot.levi.leviweb1.Thread;

import com.springboot.levi.leviweb1.Thread.scheduler.ContainerCreateJobSchedule;
import com.springboot.levi.leviweb1.Thread.scheduler.ReplenishCreateJobSchedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.TaskExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import static com.springboot.levi.leviweb1.utils.MiscUtils.createTaskExecutor;

/**
 * @author jianghaihui
 * @date 2021/6/4 18:18
 */
@Configuration
@ConditionalOnProperty(value = "SI_ROLLER.enabled", havingValue = "true")
@Slf4j
public class SiQpSchedulerConfiguration {
    private static final int SIZE = 4;


    @PostConstruct
    private void init1(){
        log.info("4444444444444444444444");
    }


    @Bean("QP_REPLENISH_JOB_CREATE_EXECUTOR")
    @ConditionalOnProperty(value = "SI_ROLLER.enabled", havingValue = "true")
    public TaskExecutor createReplenishJob() {
        return createTaskExecutor("QP_REPLENISH_JOB_CREATE_EXECUTOR", SIZE);
    }



    @Bean("QP_CONTAINER_JOB_CREATE_EXECUTOR")
    @ConditionalOnProperty(value = "SI_ROLLER.enabled", havingValue = "true")
    public TaskExecutor createContainerJob() {
        return createTaskExecutor("QP_CONTAINER_JOB_CREATE_EXECUTOR", SIZE);
    }


    @Bean("siRunner")
    @Order(2)
    public ApplicationRunner getRunner() {
        return new BootstrapRunnble();
    }

    public static class BootstrapRunnble implements ApplicationRunner {
        @Resource(name = "QP_REPLENISH_JOB_CREATE_EXECUTOR")
        private TaskExecutor replenishJobCreate;

        @Resource(name = "QP_CONTAINER_JOB_CREATE_EXECUTOR")
        private TaskExecutor containerJobCreate;

        @Resource
        private ReplenishCreateJobSchedule replenishCreateJobSchedule;
        @Resource
        private ContainerCreateJobSchedule containerCreateJobSchedule;

        @Override
        public void run(ApplicationArguments args) throws Exception {
            log.info(this.getClass().getCanonicalName());
            replenishJobCreate.execute(replenishCreateJobSchedule);
            containerJobCreate.execute(containerCreateJobSchedule);

        }
    }

}
