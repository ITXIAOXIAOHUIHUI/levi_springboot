package com.springboot.levi.leviweb1.dto;

import com.google.common.collect.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author jianghaihui
 * @date 2020/11/13 23:29
 */
@Data
@Slf4j
public class Test {

    private Long id;

    private Integer num;

    private BigDecimal price;

    private String name;

    private String category;




    public Test(Long id, Integer num, BigDecimal price, String name, String category) {
        this.id = id;
        this.num = num;
        this.price = price;
        this.name = name;
        this.category = category;
    }

    public static void main(String[] args) {
        Test prod1 = new Test(1L, 1, new BigDecimal("15.5"), "面包", "零食");
        Test prod2 = new Test(2L, 2, new BigDecimal("20"), "饼干", "零食");
        Test prod6 = new Test(2L, 2, new BigDecimal("20"), "饼干", "零食");
        Test prod3 = new Test(3L, 3, new BigDecimal("30"), "月饼", "零食");
        Test prod4 = new Test(4L, 3, new BigDecimal("10"), "青岛啤酒", "啤酒");
        Test prod5 = new Test(5L, 10, new BigDecimal("15"), "百威啤酒", "啤酒");
        Test prod7 = new Test(7L, 10, new BigDecimal("15"), "百威啤酒", "啤酒");
        List<Test> prodList = Lists.newArrayList(prod1, prod2, prod3, prod4, prod5,prod6);
        ImmutableList<Test> of = ImmutableList.copyOf(prodList);


        prodList.stream().collect(Collectors.groupingBy(Test::getId, Maps::newTreeMap, ImmutableList.toImmutableList()))
                .forEach((id,prods)->{
                    System.out.println("key::"+id);
                    System.out.println("value::"+prods);

                });

        Map<String, List<Test>> prodMap = prodList.stream().collect
                (Collectors.groupingBy(item -> item.getCategory() + "_" + item.getName()));


        System.out.println(prodMap);
        TreeMap<Long, ImmutableList<Test>> collect = prodList.stream().collect(Collectors.groupingBy(Test::getId, Maps::newTreeMap, ImmutableList.toImmutableList()));


    }

}
