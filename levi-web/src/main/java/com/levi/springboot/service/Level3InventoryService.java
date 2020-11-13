package com.levi.springboot.service;

import com.levi.springboot.dto.InventoryTransferDTO;

/**
 * @author jianghaihui
 * @date 2020/10/14 14:45
 */
public interface Level3InventoryService {

    void transferLevel3Inventory(Long warehouseId, InventoryTransferDTO transferDTO);
}
