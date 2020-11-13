package com.levi.java.genericity;

/**
 * @author jianghaihui
 * @date 2020/10/14 16:32
 */
public class GenericitryTest  {

    public static<T> T createInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        A a = createInstance(A.class);
        B b = createInstance(B.class);
    }

    class A{

    }
    class B{

    }



}
