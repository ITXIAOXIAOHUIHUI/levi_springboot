package com.levi.thread;


import java.util.HashMap;
import java.util.concurrent.*;

/**
 * @author jianghaihui
 * @date 2020/10/12 17:22
 */
public class JavaThreadAnyWhere {


    public static void main(String[] args) {
        FutureTask<Boolean> wcsReportCompleteFure = new FutureTask((Callable<Boolean>) () -> {
            try {
                test();
            } catch (Exception e) {
                return false;
            }
            return true;
        });
        /**
         * 参数说明
         *    corePoolSize：线程池中核心线程数的最大值
         *        maximumPoolSize：线程池中能拥有最多线程数
         *        workQueue：用于缓存任务的阻塞队列
         *
         *
         *        说明：
         *        如果没有空闲的线程执行该任务且当前的现场数等于corePoolSize，则添加新的线程执行该任务。
         *        如果没有空闲的线程执行该任务且当前的现场数等于corePoolSize同时阻塞队列未满，则将任务入队
         */
        ThreadPoolExecutor jobCompleteReportExecutor = new ThreadPoolExecutor(2, 2, 1L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue(1));

        jobCompleteReportExecutor.submit(wcsReportCompleteFure);

        try {
            Boolean result = wcsReportCompleteFure.get();
            System.out.println(result + "resultresultresult");
            if (result == null || !result) {
                test();
            }
        } catch (Exception e) {
            System.out.println("重试失败了，重试失败了");
            throw new RuntimeException("1111111");
        }
    }

    private static Boolean test() {
        System.out.println("hhaha");
        try {
            int a = 10 / 0;
            return true;
        } catch (Exception e) {
            throw new RuntimeException("1111111");
        }
    }
}
