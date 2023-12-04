package com.springboot.levi.leviweb1.design.daili.CGLLIBdaili;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: levi_springboot
 * @description: JDK 静态代理和JDK动态代理的区别是JDK静态代理的目标对象需要实现接口，
 *               CGLIB动态代理的目标对象不需要实现接口，但目标对象不能是final修饰的
 * @author: jhh
 * @create: 2022-11-08 15:57
 *
 * 总结：
 *  最后简单总结下静态代理、JDK动态代理和CGLIB动态代理的特点：
 *  静态代理：代理对象和目标对象都继承了同一个接口，
 *  优点：就是开头介绍代理模式的优点，扩展目标对象的功能、保护目标对象、降低代码耦合度
 *  缺点：不同的接口要有不同的代理类实现，代码会变得很冗余 JDK动态代理：实现 InvocationHandler 接口，重写 invoke 方法，然后反射生成代理类代理类字节码，并生成对象 优点：在静态代理的优点基础上，解决了静态代理的缺点 缺点：相比静态代理实现较为复杂，并且被代理的类必须实现接口，不实现接口不能使用JDK动态代理
 *  CGLIB动态代理：通过字节码技术为一个类创建子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑，来完成动态代理的实现。具体实现方式是实现 MethodInterceptor 接口，重写 intercept 方法，最后通过 Enhancer 类的回调方法来实现 优点：解决JDK动态代理存在的问题，无需接口也可以实现动态代理；采用字节码增强技术，性能较好 缺点：被代理的类不能被final修饰；技术实现上较为复杂
 */
 class SellHouse {
     public void sell(){
         System.out.println("sell sell");
     }
}
class  ProxyFacotry implements MethodInterceptor {

     private Object target;

     public ProxyFacotry(Object target){
         this.target = target;
     }
     //返回target对象的代理对象
    public Object getProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("动态代理开始");
        Object returnVal = method.invoke(target, objects);
        System.out.println("动态代理结束");
        return returnVal;
    }
}

class  Client{
    public static void main(String[] args) {
        SellHouse target = new SellHouse();
        SellHouse proxyInstance = (SellHouse)new ProxyFacotry(target).getProxyInstance();
        proxyInstance.sell();
    }
}
