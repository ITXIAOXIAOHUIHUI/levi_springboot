package com.springboot.levi.leviweb1.model;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-07-22 16:14
 */
public class ObjectKit {
    static final String GET_METHOD_PREFIX = "get";
    static final String IS_METHOD_PREFIX = "is";

    public static Object fieldValue(Object o, String fieldName) throws IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        if(o == null || StringUtils.isEmpty(fieldName)) return null;
        String getter = GET_METHOD_PREFIX + StringUtils.capitalize(fieldName), isGetter = IS_METHOD_PREFIX + StringUtils.capitalize(fieldName);

        for (Method method : ReflectionUtils.getAllDeclaredMethods(o.getClass())) {
            if (method.getParameterCount() > 0) continue;
            if (getter.equals(method.getName()) || isGetter.equals(method.getName())) {
                return method.invoke(o);
            }
        }

        Field field = ReflectionUtils.findField(o.getClass(), fieldName);
        if(field != null){
            ReflectionUtils.makeAccessible(field);
            return ReflectionUtils.getField(field, o);
        } else {
            throw new RuntimeException(o.getClass().getName() + " doesn't have the field <" + fieldName + ">");
        }
    }

    private static final int FIELD_BUILDER_CAPACITY = 16;
    private static final int PATH_BUILDER_CAPACITY = 32;

    public static Object pathFieldValue(String path, Object o) throws IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        if(path == null || o == null) {
            return null;
        }
        StringBuilder field = new StringBuilder(FIELD_BUILDER_CAPACITY), subPath = new StringBuilder(PATH_BUILDER_CAPACITY);
        boolean dotted = false;
        for(int i=0; i<path.length(); i++) {
            char c = path.charAt(i);
            if(dotted) {
                subPath.append(c);
            } else if(c == '.') {
                dotted = true;
            } else {
                field.append(c);
            }
        }

        return subPath.length() > 0 ? pathFieldValue(subPath.toString(), ObjectKit.fieldValue(o, field.toString())) : ObjectKit.fieldValue(o, field.toString());
    }
}
