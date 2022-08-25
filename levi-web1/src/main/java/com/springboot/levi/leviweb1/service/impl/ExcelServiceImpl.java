package com.springboot.levi.leviweb1.service.impl;

import com.springboot.levi.leviweb1.model.FiledMapper;
import com.springboot.levi.leviweb1.service.ExcelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-07-22 16:29
 */
@Service
public class ExcelServiceImpl implements ExcelService {


    @Override
    public LinkedHashMap<String, String> getFiledMapping(String lang, Class clazz) {
        LinkedHashMap<String, String> filedMap = new LinkedHashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            FiledMapper annotation = field.getAnnotation(FiledMapper.class);
            if (Objects.nonNull(annotation)) {
                if ("en-us".equals(lang)) {
                    filedMap.put(field.getName(), StringUtils.isEmpty(annotation.en()) ? field.getName() : annotation.en());
                } else {
                    filedMap.put(field.getName(), annotation.value());
                }
            }
        }
        return filedMap;
    }
}
