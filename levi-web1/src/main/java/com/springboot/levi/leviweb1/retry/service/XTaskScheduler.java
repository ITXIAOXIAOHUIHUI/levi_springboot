package com.springboot.levi.leviweb1.retry.service;

import com.springboot.levi.leviweb1.retry.eums.RetryTaskType;
import com.springboot.levi.leviweb1.utils.Clock;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author jianghaihui
 * @date 2021/7/4 11:08
 */
@Component
@ConfigurationProperties(prefix = "levi-interface.x-task-scheduler")
@Slf4j
public class XTaskScheduler implements IRetryTaskService {

    private final Clock clock = Clock.defaultClock();

    private final AtomicBoolean stopping = new AtomicBoolean();


    //线程池
    private ThreadPoolExecutor executor;

    @Setter
    private String nameSuffix = "";
    @Setter
    private int poolSize = 2;


    @Setter
    private String possessor = "evo-interface-node-1";

    @Override
    public boolean scheduleWithFixedDelay(long interval, TimeUnit unit, RetryTaskType type, String id, int maxRetries) {
        return false;
    }


    @PostConstruct
    public void start() {
        executor = new ThreadPoolExecutor(poolSize, poolSize, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new NamedThreadFactory("XTaskScheduler" + nameSuffix)) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                if (t == null && r instanceof Future) {
                    try {
                        ((Future) r).get();
                    } catch (InterruptedException | ExecutionException e) {
                        t = new RuntimeException("retry get enable <Future> task done.", e);
                    }
                }
                if (t != null) {
                    log.warn("Unexpected error during execution of pool." + t.getLocalizedMessage(), t);
                }
            }
        };

        // commit tasks to executor
        for (int i = 0; i < executor.getCorePoolSize(); i++) {
           // executor.submit(this::pollAndRun);
        }
        log.info("XTaskScheduler started {} workers", executor.getCorePoolSize());
    }


    /*private void pollAndRun() {
        while (!stopping.get()) {
            log.info("ttttttttttttttttt");
        }
    }*/
}
