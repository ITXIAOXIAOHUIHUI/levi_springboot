package com.springboot.levi.leviweb1.design.daili.JDKdaili;

import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-07 14:33
 */
public interface ISellHouse {
    void tell();
    void eat();
}

class SellHoseService implements ISellHouse{

    @Override
    public void tell() {
        System.out.println("tell tell");
    }

    @Override
    public void eat() {
        System.out.println("eat eat");
    }
}

class proxyClassService{
    private Object target;

    public proxyClassService(Object target){
        this.target = target;
    }

    public Object getProxInstance(){

        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("动态代理开始");
                        Object invoke = method.invoke(target, args);
                        System.out.println("动态代理结束");
                        return invoke;
                    }
                });
    }

    public static void main(String[] args) {
        //创建目标对象
        ISellHouse target = new SellHoseService();

        //创建代理对象
        ISellHouse proxyInstance = (ISellHouse)new proxyClassService(target).getProxInstance();

        proxyInstance.tell();
        proxyInstance.eat();
    }

}
