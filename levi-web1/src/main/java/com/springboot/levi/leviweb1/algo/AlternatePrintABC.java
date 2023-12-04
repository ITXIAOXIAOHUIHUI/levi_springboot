package com.springboot.levi.leviweb1.algo;

class SharedResource {
    public int currentThread = 1; // 1: ThreadA, 2: ThreadB, 3: ThreadC

    public int getCurrentThread() {
        return currentThread;
    }

    public void setCurrentThread(int currentThread) {
        this.currentThread = currentThread;
    }

    @Override
    public String toString() {
        return "SharedResource{" +
                "currentThread=" + currentThread +
                '}';
    }
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

    public SharedResource getSharedResource() {
        return sharedResource;
    }

    public String getMessage() {
        return message;
    }

    public int getTargetThread() {
        return targetThread;
    }

    @Override
    public String toString() {
        return "PrintThread{" +
                "sharedResource=" + sharedResource +
                ", message='" + message + '\'' +
                ", targetThread=" + targetThread +
                '}';
    }

    @Override
    public void run() {
        System.out.println("before enter"+this.sharedResource +"before enter"+this.targetThread);
        synchronized (sharedResource) {
            System.out.println("enter:"+sharedResource+"enter:"+this.targetThread);
            try {
                while (sharedResource.currentThread != targetThread) {
                    System.out.println("center:"+sharedResource+"currentThread"+sharedResource.currentThread+"targetThread"+targetThread);
                    // 当前线程不是目标线程，等待
                    sharedResource.wait();
                }
                System.out.println();
                System.out.print(message);
                System.out.println("end:"+sharedResource+"currentThread"+sharedResource.currentThread+"targetThread"+targetThread);
                // 切换到下一个线程
                sharedResource.currentThread = (sharedResource.currentThread % 3) + 1;

                // 唤醒下一个线程
                sharedResource.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class AlternatePrintABC {
    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource();

        Thread threadA = new Thread(new PrintThread(sharedResource, "A", 1));
        Thread threadB = new Thread(new PrintThread(sharedResource, "B", 2));
        Thread threadC = new Thread(new PrintThread(sharedResource, "C", 3));

        threadB.start();
        Thread.sleep(1000L);
        threadC.start();
        Thread.sleep(1000L);

        threadA.start();
        Thread.sleep(1000L);


    }
}
