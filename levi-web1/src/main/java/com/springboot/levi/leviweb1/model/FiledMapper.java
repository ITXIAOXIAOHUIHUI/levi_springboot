package com.springboot.levi.leviweb1.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangguanglin
 *
 * Excel 表头解析时，用作表头字段匹配
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FiledMapper {
    /**
     * 中文表头
     * @return
     */
    String value();

    /**
     * 英文表头
     * @return
     */
    String en() default "";
}
