package com.levi.design.pattern.iteratorPattern;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jianghaihui
 * @date 2019/9/24 18:29
 */
public class Test {

    public static void main(String[] args) {


        List<String> lines = Arrays.asList("spring", "node", "mkyong", "mkyong");

        System.out.println(lines);
        String item = lines.stream().filter(line -> "mkyong".equals(line)).findAny().orElse(null);
        System.out.println(item);
        List<String> list = lines.stream().filter(line -> !"mkyong".equals(line)).collect(Collectors.toList());
        list.forEach(System.out::println);
    }
}
