package com.springboot.levi.leviweb1.service;

import java.util.LinkedHashMap;

public interface ExcelService {

    public LinkedHashMap<String, String> getFiledMapping(String lang, Class clazz);
}
