package com.levi.springmachine.spring;

/**
 * @author jianghaihui
 * @date 2020/10/22 11:46
 */
public class FactoryBeanServiceImpl implements FactoryBeanService {

    @Override
    public void testFacotoryBean() {
        System.out.println("我是FactoryBean的一个测试了");
    }
}
