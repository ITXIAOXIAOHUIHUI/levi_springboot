package com.springboot.levi.leviweb1.java;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @program: levi_springboot
 * @description: BlockingQueue:
 *                     四个实现的队列：
                         *常见的4种阻塞队列
                         * ArrayBlockingQueue 由数组支持的有界队列
                         * LinkedBlockingQueue 由链接节点支持的可选有界队列
                         * PriorityBlockingQueue 由优先级堆支持的无界优先级队列
                         * DelayQueue 由优先级堆支持的、基于时间的调度队列
 * @author: jhh
 * @create: 2022-11-16 14:07
 */
public class ArrayBlockingQueueTest {

    /**根据bucket topFace 和作业面得到作业点方向*/
    private static final int[][] BUCKET_SLOT_WORK_FACE = {
            {0, 0, 0, 0, 0},
            {0, 3, 4, 1, 2},
            {0, 2, 3, 4, 1},
            {0, 1, 2, 3, 4},
            {0, 4, 1, 2, 3},
    };

    public static void main(String[] args) {
        System.out.println(BUCKET_SLOT_WORK_FACE[4][1]);
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(6);
        BlockingQueue<String> linkBlockingQueue = new LinkedBlockingDeque<>();
        BlockingQueue<String> delayBlockingQueue = new DelayQueue();

    }
}
