package com.levi.springboot.utils;

import lombok.Data;

/**
 * @author jianghaihui
 * @date 2019/9/15 19:37
 */
@Data
public class ResourceNotFoundExceptionExt extends   RuntimeException {
    private String message;

    public ResourceNotFoundExceptionExt(String message){
        this.message = message;
    }
}
