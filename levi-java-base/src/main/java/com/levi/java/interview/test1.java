package com.levi.java.interview;

import java.util.HashSet;

/**
 * @author jianghaihui
 * @date 2020/10/14 16:41
 */
public class test1 {



    public static void main(String[] args) {
        String a = new String("ab"); // a 为一个引用
        String b = new String("ab"); // b为另一个引用,对象的内容一样
        String aa = "ab"; // 放在常量池中
        String bb = "ab"; // 从常量池中查找
        if (aa == bb) // true
            System.out.println("aa==bb");
        if (a == b) // false，非同一对象
            System.out.println("a==b");
        if (a.equals(b)) // true
            System.out.println("aEQb");
        if (42 == 42.0) { // true
            System.out.println("true");
        }
        System.out.println();
        HashSet<A> hashSet = new HashSet<>();
        A  a1 = new A ("100","100");
        A  a2 = new A ("100","100");
        hashSet.add(a1);
        hashSet.add(a2);
        System.out.println("hashSet:"+hashSet.size());
        hashSet.forEach(h->{
            System.out.println(h.name);
        });




    }




    static class A{
        private String name;
        private String userName;

        public  A(String name, String userName) {
            this.name = name;
            this.userName = userName;
        }
    }

    /**
     * String 中的 equals 方法是被重写过的，因为 Object 的 equals 方法是比较的对象的内存地址，
     *      而 String 的 equals 方法比较的是对象的值。
     * 当创建 String 类型的对象时，虚拟机会在常量池中查找有没有已经存在的值和要创建的值相同的对象，
     *      如果有就把它赋给当前引用。如果没有就在常量池中重新创建一个 String 对象。
     */
}
