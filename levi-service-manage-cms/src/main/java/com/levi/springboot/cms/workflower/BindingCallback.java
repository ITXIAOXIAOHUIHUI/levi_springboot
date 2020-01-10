package com.levi.springboot.cms.workflower;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author jianghaihui
 * @date 2019/10/11 11:30
 */
@Retention(RUNTIME)
@Target({ METHOD })
public @interface BindingCallback {
    String actionId();
}
