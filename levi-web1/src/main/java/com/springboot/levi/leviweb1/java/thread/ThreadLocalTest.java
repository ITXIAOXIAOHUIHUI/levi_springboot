package com.springboot.levi.leviweb1.java.thread;

import com.springboot.levi.leviweb1.wcs.Test;
import org.thymeleaf.util.MapUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2023-02-14 11:18
 */
public class ThreadLocalTest {

    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();

    ThreadLocal<String> stringLocal = new ThreadLocal<String>();

    public void set(){
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong(){
        return longLocal.get();
    }
    public String getString(){
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        String str = null;
        String str1 = "11";
        if(str != null || str1.equals("11")){
            System.out.println("1111");
        }

    }
}
