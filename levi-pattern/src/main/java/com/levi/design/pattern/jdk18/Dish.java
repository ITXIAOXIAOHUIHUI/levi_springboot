package com.levi.design.pattern.jdk18;

import lombok.Data;

import javax.annotation.security.DenyAll;

/**
 * @author jianghaihui
 * @date 2019/10/28 10:42
 */
@Data
public class Dish {

    private String food;

    private boolean flag;

    private int price;
    private String meat;

    public Dish(String food, boolean flag, int price, String meat) {
        this.food = food;
        this.flag = flag;
        this.price = price;
        this.meat = meat;
    }

}
