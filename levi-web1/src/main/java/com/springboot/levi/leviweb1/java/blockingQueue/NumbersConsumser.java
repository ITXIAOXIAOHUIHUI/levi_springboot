package com.springboot.levi.leviweb1.java.blockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-16 14:15
 */
public class NumbersConsumser implements Runnable {
    private BlockingQueue<Integer> queue;

    private final int posionPill;

    public NumbersConsumser(BlockingQueue<Integer> queue,int posionPill) {
        this.queue = queue;
        this.posionPill = posionPill;
    }

    @Override
    public void run() {
        try{
         while(true){
             Integer number = queue.take();
             if(number.equals(posionPill)){
                 return;
             }
             System.out.println("武大郎-{}"+Thread.currentThread()+"号，喝药-编号"+number);
         }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        int a = 2 << 1;
        System.out.println(a);
    }
}
