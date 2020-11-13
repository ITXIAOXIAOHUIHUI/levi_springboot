package com.levi.springboot.mock;

import com.levi.springboot.utils.JsonUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jianghaihui
 * @date 2020/10/22 11:12
 */
@Component
@Lazy
public class MockDataBuilder {

    public static final Map<String,Object> DATA_CACHE = new HashMap<>();


    public static <T> List<T> loadData(String fileName ,Class<T> dataType){
        if(DATA_CACHE.containsKey(fileName)){
            return (List<T>) DATA_CACHE.get(fileName);
        }
        List<T> data = JsonUtil.parse(MockDataBuilder.class.getResourceAsStream("/mock/data"+fileName+".json"),List.class,dataType);
        DATA_CACHE.put(fileName,data);
        return data;
    }

}
