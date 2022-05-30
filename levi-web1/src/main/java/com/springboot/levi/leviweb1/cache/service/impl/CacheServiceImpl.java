package com.springboot.levi.leviweb1.cache.service.impl;

import com.springboot.levi.leviweb1.cache.service.AbstractGuavaCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author jianghaihui
 * @date 2021/7/12 14:01
 */
@Service
@Slf4j
public class CacheServiceImpl<String,Object> extends AbstractGuavaCache {



    public void getCacheByKey(){
        this.fetchData("kckq");
    }


    @Override
    public java.lang.Object fetchData(java.lang.Object key) {
        log.info("test");
        return "test";
    }
}
