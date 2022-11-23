package com.springboot.levi.leviweb1.Thread;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-14 16:47
 */
public class ThreadLocalTest {
    //声明threadLocal为静态字段
    public static final ThreadLocal<String> threadLocal=new ThreadLocal<>();

    public static void main(String[] args) {
        Thread thread1=new Thread(()->{
            threadLocal.set(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName()+"Thread1中共享变量的副本:"+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+"Thread1中未共享变量的副本，值为:"+threadLocal.get());
        },"Thread==>1");

        Thread thread2=new Thread(()->{
            System.out.println(  );
            threadLocal.set(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName()+"Thread2中共享变量的副本:"+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+"删除后...");
            threadLocal.remove();
            System.out.println(Thread.currentThread().getName()+"Thread2中未共享变量的副本，值为:"+threadLocal.get());

        },"Thread==>2");
        thread1.start();
        thread2.start();
    }
}


/**
 * 由上面的源码可以看出，每个线程保存本地变量实际上是保存在threadLocals中的，
 * 并不是保存在ThreadLocal实例对象中的。
 * 当线程第一次调用ThreadLocal的set()方法和get()方法会实例化对象，ThreadLocal类提供了set()和get()方法，用来存储和读取线程本地变量的值。
 * 调用set()方法会把要存储的值存储在调用方法的线程的threadLocal变量中，调用get()方法会从当前线程的threadLocals变量中获取保存的值。下面对ThreadLocal类中的方法进行分析。
 *
 * **/