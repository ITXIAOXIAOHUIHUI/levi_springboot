package com.springboot.levi.leviweb1.utils.handler;

import java.util.concurrent.*;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-09-19 16:28
 */
public class ExtensionLoader {
    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }


}
