package com.levi.springboot.cms.workflower;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jianghaihui
 * @date 2019/10/11 11:20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Home {

    //成员
    String[] members();
    //地址
    String address();
}
