package com.levi.springboot.cms.workflower;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jianghaihui
 * @date 2019/10/11 11:18
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Region {
    //地区名称
    String name();

    //所属国家
    String country();
}
