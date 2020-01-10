package com.levi.design.pattern.lock;

/**
 * @author jianghaihui
 * @date 2019/12/27 11:52
 */
public class OrderService implements  Runnable {
    private OrderNumGenerator ong = new OrderNumGenerator(); //定义成全局的

    @Override
    public void run() {
        getNumber();
    }
    public void getNumber(){
        String number = ong.getNumber();
        System.out.println(Thread.currentThread().getName()+"number"+number);
    }

    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        //开启100个线程
        for(int i = 0 ;i < 100 ;i++){
            new Thread(orderService).start();

        }
    }
}
