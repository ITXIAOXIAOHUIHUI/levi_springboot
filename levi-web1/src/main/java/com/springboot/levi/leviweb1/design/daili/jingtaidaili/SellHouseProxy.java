package com.springboot.levi.leviweb1.design.daili.jingtaidaili;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-07 14:18
 */
public class SellHouseProxy implements ISellHouse {
    private ISellHouse target;

    public SellHouseProxy (ISellHouse target){
        this.target = target;
    }
    @Override
    public void sell() {
        System.out.println("代理开始了");
        target.sell();

        System.out.println("代理结束了");
    }

    @Override
    public void eat() {
        System.out.println("代理吃饭了");
        target.eat();

        System.out.println("代理吃完饭了");
    }

    public static void main(String[] args) {
        SellHouse sellHouse = new SellHouse();
        SellHouseProxy sellHouseProxy = new SellHouseProxy(sellHouse);
        sellHouseProxy.sell();
    }
}
