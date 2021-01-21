package com.springboot.levi.leviweb1.service;

import com.springboot.levi.leviweb1.dto.InventoryTransferDTO;

/**
 * @author jianghaihui
 * @date 2020/10/14 14:45
 */
public interface Level3InventoryService {

    void transferLevel3Inventory(Long warehouseId, InventoryTransferDTO transferDTO);
}
