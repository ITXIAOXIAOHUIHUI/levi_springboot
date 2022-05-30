package com.levi.springboot.consumer.thread;

/**
 * @author jianghaihui
 * @date 2020/4/2 14:17
 */

public class AsyncThread implements Runnable {

    @Override
    public void run() {
        System.out.println("start async thread");
        for (int i = 0; i < 10000; i++) {

        }
    }

    private void test01(int a) {
        System.out.println(a);
    }
}
