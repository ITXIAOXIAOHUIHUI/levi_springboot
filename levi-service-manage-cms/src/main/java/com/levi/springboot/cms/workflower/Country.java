package com.levi.springboot.cms.workflower;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jianghaihui
 * @date 2019/10/11 11:14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Country {

    //国家名称
    String name();
    //国家语言
    String[] languages();
}
