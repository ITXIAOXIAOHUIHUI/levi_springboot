package com.levi.design.pattern.strategyPattern;

/**
 * @author jianghaihui
 * @date 2019/10/23 13:45
 */
public class Context {
    //维持一个对象抽象策略类的引用
    private AbstractStrategy strategy;

    public void setStrategy(AbstractStrategy strategy){
        this.strategy = strategy;
    }

    //调用策略类中的算法
    public void algorithm() {
        strategy.algorithm();
    }
}
