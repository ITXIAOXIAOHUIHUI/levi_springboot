package com.springboot.levi.leviweb1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-10-16 18:39
 */
public class Demo {
     boolean initFlag = true;

    public void test1(){
        System.out.println(Thread.currentThread().getName());

        while(initFlag){

        }

    }

    public static void main(String[] args) throws InterruptedException {
        //这里自定义一个需要排序的map集合
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("A", 98);


        map.put("B", 50);


        map.put("C", 76);


        map.put("D", 23);


        map.put("E", 85);
        int size = map.size();

        //通过map.entrySet()将map转换为"1.B.1.e=78"形式的list集合
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(size);
        list.addAll(map.entrySet());
        List<String> keys = list.stream()
                .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed())
                .map(Map.Entry<String, Integer>::getKey)
                .collect(Collectors.toList());
        System.out.println(keys);
    }

}
