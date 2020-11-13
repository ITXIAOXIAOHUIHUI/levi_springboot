package com.levi.design.pattern;

import com.levi.design.pattern.lock.PickingLockObjectFactory;

/**
 * @author jianghaihui
 * @date 2020/10/27 15:00
 *      你要知道的synchronized的原理
 *           这是互斥锁：意味着最多只能一个线程获得这个锁
 *      对象锁：用于对象实例方法上，或者一个对象实例上的
 *      类锁：用于类的静态方法或者一个类的class对象上的
 *              类的对象实例可以有很多个，但是每个类只有一个class对象，所以不同对象实例的对象锁是互不干扰的，
 *              但是每个类只有一个类锁。但是有一点必须注意的是，其实类锁只是一个概念上的东西，并不是真实存在的，
 *              它只是用来帮助我们理解锁定实例方法和静态方法的区别的
 *      用法：
 *          synchronized修饰方法
 *          synchronized修饰代码块。
 *
 *      特点：
 *          类锁修饰方法和代码块的效果和对象锁是一样的，因为类锁只是一个抽象出来的概念，只是为了区别静态方法的特点，
 *          因为静态方法是所有对象实例共用的，所以对应着synchronized修饰的静态方法的锁也是唯一的，所以抽象出来个类锁。
 *          其实这里的重点在下面这块代码，
 *
 */
public class Lock {

    public static void main(String[] args) {

        System.out.println( PickingLockObjectFactory.getInstance()+"+++");
    }


    public void testLock(){
            synchronized (PickingLockObjectFactory.getInstance().getShortReGeneratorLock()){

            }
    }
}
