package com.springboot.levi.leviweb1.design.single;

import io.reactivex.Single;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-07 13:53
 */
public class Singleton {


    /*private static class Signleton2{
        private static final Singleton instance = new Singleton();
    }
    private Singleton(){

    }
    public static final Singleton getInstance(){
        return Signleton2.instance;
    }*/

    private volatile static Singleton singleton;

    private Singleton(){

    }

    public  static  Singleton getSingleton(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {

    }
}
