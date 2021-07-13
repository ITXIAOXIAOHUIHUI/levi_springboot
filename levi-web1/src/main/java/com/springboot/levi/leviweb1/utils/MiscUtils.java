package com.springboot.levi.leviweb1.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jianghaihui
 * @date 2021/6/5 11:55
 */
@Slf4j
public class MiscUtils {



    /**
     * 创建线程池，
     * @param name 线程池名称
     * @param sz 大小
     * @return
     */
    public static TaskExecutor createTaskExecutor (String name, int sz) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(sz);
        executor.setMaxPoolSize(sz * 2);
        executor.setQueueCapacity(20);
        executor.setKeepAliveSeconds(60);

        //如果是调度runnable, 将最老的一个runnable 清除，  如果是其他的runnable ， 直接拒绝接受
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                if (r.getClass().getCanonicalName().toLowerCase().contains("bootstrap")) {
                    //调度线程不能被丢弃
                    if (!e.isShutdown()) {
                        Runnable old = e.getQueue().poll();
                        e.execute(r);
                        if (old != null) {
                            log.warn("for receive job: " + r.getClass().getName() + ", rejectOldest: " + old.getClass().getName());
                        }
                    }
                } else {
                    log.warn("reject " + r.getClass().getName());
                    super.rejectedExecution(r, e);
                }
            }
        });
        executor.setThreadFactory(new ThreadFactory() {
            private AtomicInteger counter = new AtomicInteger(1);

            @Override
            public Thread newThread(@Nonnull Runnable r) {
                return new Thread(Thread.currentThread().getThreadGroup(), r, name + "-" + counter.getAndIncrement(), 0);
            }
        });
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }














}
