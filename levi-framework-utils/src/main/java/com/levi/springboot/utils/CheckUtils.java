package com.levi.springboot.utils;

import com.levi.springboot.code.IErrorCodeConstant;
import com.levi.springboot.exception.ValidatorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 传入的参数校验
 * @author jianghaihui
 * @date 2020/1/10 10:58
 */
public class CheckUtils {


    /**
     * 入参判空
     * @param obj
     * @param name
     */
    public static void checkNull(Object obj,String name) throws ValidatorException {
        if(null == obj){
            throw new ValidatorException(IErrorCodeConstant.PARAMETER_IS_NULL,new String[]{name});
        }
    }

    public static void checkEmptyStr(String str,String name) throws ValidatorException {
        if(StringUtils.isNoneBlank(str)){
            throw new ValidatorException(IErrorCodeConstant.PARAMETER_IS_NULL,new String[]{name});
        }
    }

    public static void checkEmptyCollection(Collection<?> collection,String name) throws ValidatorException {
        if(CollectionUtils.isEmpty(collection)){
             throw new ValidatorException(IErrorCodeConstant.PARAMETER_IS_NULL ,  new String[]{name});
        }

    }
















}
