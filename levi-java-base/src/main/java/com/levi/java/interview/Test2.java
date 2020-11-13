package com.levi.java.interview;

/**
 * @author jianghaihui
 * @date 2020/10/14 17:45
 */
public class Test2 {
    public static void main(String[] args) {
        Double d1 = 10.00;
        Double d2 = 10.00;

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;
        System.out.println(c==d);   //true
        System.out.println(e==f);    //false
        System.out.println(c==(a+b)); //true
        System.out.println(c.equals(a+b)); //true
        System.out.println(g==(a+b)); //true
        System.out.println(g.equals(a+b)); //false
        System.out.println(g.equals(a+h));//true
    }



}
