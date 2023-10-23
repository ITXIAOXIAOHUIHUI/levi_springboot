package com.springboot.levi.leviweb1.design.chaindesign;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-12-29 14:52
 */
@Data
public class ProductVO {

    /**
     * 商品SKU，唯一
     */
    private Long skuId;
    /**
     * 商品名称
     */
    private String skuName;
    /**
     * 商品图片路径
     */
    private String Path;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 库存
     */
    private Integer stock;

    public ProductVO(Long skuId) {
        this.skuId = skuId;
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","3");
        map.forEach((key,value)->{
            if(key.equals("1")){
                return;
            }
            System.out.println("0000"+key);
        });
    }




}
