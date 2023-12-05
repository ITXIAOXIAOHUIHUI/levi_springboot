package com.springboot.levi.leviweb1.algo;


/**
 * 线程间交替打印 A B C
 */
class SharedResource {
    public int currentThread = 1; // 1: ThreadA, 2: ThreadB, 3: ThreadC
}

class PrintThread implements Runnable {
    private final SharedResource sharedResource;
    private final String message;
    private final int targetThread;

    public PrintThread(SharedResource sharedResource, String message, int targetThread) {
        this.sharedResource = sharedResource;
        this.message = message;
        this.targetThread = targetThread;
    }

    @Override
    public void run() {
        synchronized (sharedResource) {
            try {
                for (int i = 0; i < 5; i++) { // 控制打印次数，这里设定为5次
                    while (sharedResource.currentThread != targetThread) {
                        // 当前线程不是目标线程，等待
                        sharedResource.wait();
                    }

                    System.out.print(message);

                    // 切换到下一个线程
                    sharedResource.currentThread = (sharedResource.currentThread % 3) + 1;

                    // 唤醒下一个线程
                    sharedResource.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class AlternatePrintABC {
    public static void main(String[] args) {

        System.out.println("iii"+(1%3));
        System.out.println("iii"+(2%3));
        SharedResource sharedResource = new SharedResource();

        Thread threadA = new Thread(new PrintThread(sharedResource, "A", 1));
        Thread threadB = new Thread(new PrintThread(sharedResource, "B", 2));
        Thread threadC = new Thread(new PrintThread(sharedResource, "C", 3));

        threadA.start();
        threadB.start();
        threadC.start();
    }


}
