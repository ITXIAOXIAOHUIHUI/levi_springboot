package com.springboot.levi.leviweb1.design.daili.jingtaidaili;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-07 14:17
 */
public class SellHouse implements ISellHouse {
    @Override
    public void sell() {
        System.out.println("hello mai fang le ");
    }

    @Override
    public void eat() {
        System.out.println("eat eat");
    }
}
