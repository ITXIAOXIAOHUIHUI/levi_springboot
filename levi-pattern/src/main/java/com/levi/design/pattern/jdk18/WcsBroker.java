package com.levi.design.pattern.jdk18;

import java.util.Set;

/**
 * @author jianghaihui
 * @date 2020/1/16 11:09
 */
public interface WcsBroker {

    /**
     * 注册
     */
    void register();

    /**
     * 初始化
     */
    void init();

    /**
     * 返回broker的类型
     *
     * @return BrokerType
     */
    BrokerType getType();

    /**
     * 返回仓库ID
     *
     * @return warehouseId
     */
    Long getWarehouseId();

    /**
     * 返回库区codes
     *
     * @return zoneCodes
     */
    Set<String> getZoneCodes();
}
