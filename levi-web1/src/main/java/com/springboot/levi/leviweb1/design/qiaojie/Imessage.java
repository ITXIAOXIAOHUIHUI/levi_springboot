package com.springboot.levi.leviweb1.design.qiaojie;

/**
 * @program: levi_springboot
 * @description: 桥接模式
 * 抽象化角色 Abstraction：定义抽象的接口，包含一个对实现化角色的引用，抽象角色的方法需要调用实现化角色；
 * 扩展抽象化角色 RefinedAbstraction：抽象化角色的子类，一般对抽象部分的方法进行完善和扩展，实现父类中的业务方法，并通过组合/聚合关系调用实现化角色中的业务方法实现化角色
 * Implementor：定义具体行为、具体特征的应用接口，供扩展抽象化角色使用，一般情况下是由实现化角色提供基本的操作，而抽象化角色定义基于实现部分基本操作的业务方法；
 * 具体实现化角色 ConcreteImplementor：完善实现化角色中定义的具体逻辑。
 *
 * 作者：程序员小徐同学
 * 链接：https://juejin.cn/post/7111654026701176862
 * 来源：稀土掘金
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @author: jhh
 * @create: 2022-07-05 16:28
 */
public interface Imessage {

    void sendMsg(String user,String message);
}
