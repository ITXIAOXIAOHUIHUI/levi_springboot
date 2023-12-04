package com.springboot.levi.leviweb1.design.ServiceDeLocatorPattern;

import javassist.compiler.Parser;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.FactoryBean;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2023-02-27 10:23
 */

/**
 * 1、让我们定义我们的服务定位器接口ParserFactory,它有一个接受内容类型参数并返回Parser的方法
 */
public interface ParserFactory {
    Parser getParser(ContentType contentType);
}
