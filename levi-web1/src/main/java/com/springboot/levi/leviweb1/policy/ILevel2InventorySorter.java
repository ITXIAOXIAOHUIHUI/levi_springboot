package com.springboot.levi.leviweb1.policy;

/**
 * @author jianghaihui
 * @date 2021/1/25 16:48
 */
public interface ILevel2InventorySorter {

    /**
     * 对于2级库存排序 做计算
     *
     * @param orderType
     * @param warehouseId
     * @return
     */
    int calculateSortNumber(String orderType, Long warehouseId);
}
