package com.springboot.levi.leviweb1.config;

import com.springboot.levi.leviweb1.scheduler.ContainerCreateJobSchedule;
import com.springboot.levi.leviweb1.scheduler.ReplenishCreateJobSchedule;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.TaskExecutor;

import javax.annotation.Resource;

import static com.springboot.levi.leviweb1.utils.MiscUtils.createTaskExecutor;

/**
 * @author jianghaihui
 * @date 2021/6/4 18:18
 */
@Configuration
//@ConditionalOnProperty(value = WorkMode.SI_QUICKPICK, havingValue = WorkMode.value)
public class SiQpSchedulerConfiguration {

    private static final int SIZE = 4;


    /**
     * 创建上架任务
     *
     * @return
     */
    @Bean("QP_REPLENISH_JOB_CREATE_EXECUTOR")
    //@ConditionalOnProperty(value = WorkMode.SI_QUICKPICK, havingValue = WorkMode.value)
    public TaskExecutor createReplenishJob() {
        return createTaskExecutor("QP_REPLENISH_JOB_CREATE_EXECUTOR", SIZE);
    }


    /**
     * 创建容器入场
     *
     * @return
     */
    @Bean("QP_CONTAINER_JOB_CREATE_EXECUTOR")
   // @ConditionalOnProperty(value = WorkMode.SI_QUICKPICK, havingValue = WorkMode.value)
    public TaskExecutor createContainerJob() {
        return createTaskExecutor("QP_CONTAINER_JOB_CREATE_EXECUTOR", SIZE);
    }


    @Bean("siQuickPickRunner")
    @Order(2)
   // @ConditionalOnProperty(value = WorkMode.SI_QUICKPICK, havingValue = WorkMode.value)
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
            replenishJobCreate.execute(replenishCreateJobSchedule);
            containerJobCreate.execute(containerCreateJobSchedule);

        }
    }

}
