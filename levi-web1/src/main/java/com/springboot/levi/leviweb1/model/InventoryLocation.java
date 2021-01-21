/******************************************************************************
 * Copyright (C) 2019 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 * 版权所有 翻版必究
 *****************************************************************************/
package com.springboot.levi.leviweb1.model;

import com.springboot.levi.leviweb1.enums.ZoneTypeEnum;
import lombok.Data;

/**
 * 三级库存转移目的地
 *
 * @author chengjinzhi
 * @history 2019-3-13
 */
@Data
public class InventoryLocation {

    private InventoryLocation() {
    }

    private boolean virtual;

    private String zoneCode;

    private String bucketCode;

    private String bucketSlotCode;

    private ZoneTypeEnum virtualZoneType;

    private String level1ContainerCode;

    private String level2ContainerCode;

    public static InventoryLocation virtual(ZoneTypeEnum virtualZoneType, String virtualLevel1ContainerCode, String virtualLevel2ContainerCode) {
        if (!virtualZoneType.isVirtual()) {
            throw new RuntimeException("zoneType must be virtual");
        }
        InventoryLocation location = new InventoryLocation();
        location.setVirtual(true);
        location.setVirtualZoneType(virtualZoneType);
        location.setLevel1ContainerCode(virtualLevel1ContainerCode);
        location.setLevel2ContainerCode(virtualLevel2ContainerCode);
        return location;
    }

    public static InventoryLocation real(String bucketCode, String bucketSlotCode, String level1ContainerCode, String level2ContainerCode) {
        InventoryLocation location = new InventoryLocation();
        location.setVirtual(false);
        location.setBucketCode(bucketCode);
        location.setBucketSlotCode(bucketSlotCode);
        location.setLevel1ContainerCode(level1ContainerCode);
        location.setLevel2ContainerCode(level2ContainerCode);
        return location;
    }
}
