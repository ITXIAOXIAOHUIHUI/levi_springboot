package com.springboot.levi.leviweb1.design.chaindesign;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-12-29 14:53
 */
@Component
public class AbstractCheckHandler {

    /**
     * 处理处理器持有下一个处理器的引用
     */
    @Getter
    @Setter
    protected  AbstractCheckHandler nextHandler;

    private static String test = "A";


    public  ProductCheckHandlerConfig productCheckHandlerConfig;

    public static void main(String[] args) {
        test = "B";
    }

}
