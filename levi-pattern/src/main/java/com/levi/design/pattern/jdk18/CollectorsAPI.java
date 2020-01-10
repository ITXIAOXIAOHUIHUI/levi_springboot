package com.levi.design.pattern.jdk18;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jianghaihui
 * @date 2019/10/28 10:40
 */
public class CollectorsAPI {

    public static final List<Dish> menu =  Arrays.asList(
            new Dish("pork", false, 800, "1"),
            new Dish("beef", false, 700, "1"),
            new Dish("chicken", false, 400, "1"),
            new Dish("french fries", true, 530, "2"),
            new Dish("rice", true, 350, "2"),
            new Dish("season fruit", true, 120, "2"),
            new Dish("pizza", true, 550, "2"),
            new Dish("prawns", false, 300, "3"),
            new Dish("salmon", false, 450, "3"));

    public static void main(String[] args) {
        //1.求平均值
        //testAveragingDouble();
        //testaveragingInt();
       // testAveragingLong();
        //testCollectingAndThen();

        //2.统计
       // testCounting();
       // testGroupByFunction();
       testGroupByFunctionAndCollectors();
        testGroupByFunctionAndAnCollectors();
        //testSummarizingInt();
    }

    //按照某一个属性进行统计，得出统计的数量,个数，总和，最大值，平均值
    private static void testSummarizingInt(){
        IntSummaryStatistics intSummaryStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getPrice));
        Optional.ofNullable(intSummaryStatistics).ifPresent(System.out::println);
        //IntSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}
    }

    private static void testGroupByFunctionAndAnCollectors(){
        System.out.println("...testGroupByFunctionAndAndCollectors");
        Map<String, Double> map = menu.stream().collect(Collectors.groupingBy(Dish::getFood,Collectors.averagingInt(Dish::getPrice)));
        //Optional.ofNullable(map.getClass()).ifPresent(System.out::println);
        map.entrySet().forEach(m->{
            System.out.println(m.getKey()+"++++"+m.getValue());
        });
        System.out.println("=======");
        //我们得到的是hashMap，下面把它改成treeMap
        TreeMap<String,Double> map2 = menu.stream().collect(Collectors.groupingBy(Dish::getFood,TreeMap::new,Collectors.averagingDouble(Dish::getPrice)));
        //Optional.of(map2.getClass()).ifPresent(System.out::println);
        map2.entrySet().forEach(m->{
            System.out.println(m.getKey()+"++++"+m.getValue());
        });
    }

    private static void testGroupByFunctionAndCollectors(){
        System.out.println("......testGroupByFunctionAndCollectors");
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getFood,Collectors.counting()))).ifPresent(System.out::println);

        //每个分类下卡路里平均值
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getFood , Collectors.averagingDouble(Dish::getPrice))))
                .ifPresent(System.out::println);
    }

    private static void testGroupByFunction(){
        System.out.println("....testGroupByFunction");
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getFood)))
                .ifPresent(System.out::println);
    }

    private static void testCounting(){
        System.out.println("....testGroupByFunction");
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getFood)))
                .ifPresent(System.out::println);
    }


























}
