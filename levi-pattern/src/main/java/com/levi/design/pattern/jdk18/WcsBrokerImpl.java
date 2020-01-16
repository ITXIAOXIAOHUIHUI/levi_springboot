package com.levi.design.pattern.jdk18;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author jianghaihui
 * @date 2020/1/16 11:19
 */
public class WcsBrokerImpl implements WcsBroker {

    @Override
    public void register() {
        System.out.println("hello cao xi gui");
    }

    @Override
    public void init() {

    }

    @Override
    public BrokerType getType() {
        return null;
    }

    @Override
    public Long getWarehouseId() {
        return null;
    }

    @Override
    public Set<String> getZoneCodes() {
        Set<String> set = Sets.newHashSet();
        set.add("hello");
        return set;
    }
}
