package com.springboot.levi.leviweb1.Thread;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-14 16:47
 */
public class ThreadLocalTest {
    //声明threadLocal为静态字段
    public static final ThreadLocal<String> threadLocal=new ThreadLocal<>();

    private static boolean isStop = false;
    public static void main(String[] args) throws InterruptedException {
        /*Thread thread = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while(!isStop){
                    i++;
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Stop Thread");
        isStop = true;*/
/*
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() {
                return Thread.currentThread().getName();
            }
        });

        for(int i= 0 ;i <= 100000 ; i++){
            System.out.println("threadName"+Thread.currentThread().getName()+i);
        }
        try {
            String res = future.get();
            System.out.println("resres"+res);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for(int i= 0 ;i <= 1000 ; i++){
            System.out.println("threadName"+Thread.currentThread().getName()+i);
        }*/


        Map<String,String> map = Maps.newHashMap();
        for(Map.Entry<String,String> result:map.entrySet()){
            System.out.println(result.getKey());
            System.out.println(result.getValue());
        }
        System.out.println();
        /**
         * hds空点游荡任务
         */
        int HDS_STRAY = 0X800;
        System.out.println(isBitSet(HDS_STRAY,2048));
    }



    public static boolean isBitSet(int flag, int bit) {
        return (flag & bit) == bit;
    }
}



/**
 * 由上面的源码可以看出，每个线程保存本地变量实际上是保存在threadLocals中的，
 * 并不是保存在ThreadLocal实例对象中的。
 * 当线程第一次调用ThreadLocal的set()方法和get()方法会实例化对象，ThreadLocal类提供了set()和get()方法，用来存储和读取线程本地变量的值。
 * 调用set()方法会把要存储的值存储在调用方法的线程的threadLocal变量中，调用get()方法会从当前线程的threadLocals变量中获取保存的值。下面对ThreadLocal类中的方法进行分析。
 *
 * **/