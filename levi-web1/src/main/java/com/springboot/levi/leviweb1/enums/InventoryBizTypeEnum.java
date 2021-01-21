/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 * 版权所有 翻版必究
 **************************************** *************************************/
package com.springboot.levi.leviweb1.enums;

import lombok.Getter;


public enum InventoryBizTypeEnum {
    RECEIVING("收货"),
    REPLENISHMENT("上架"),
    PICKING_LOCK("库存预占"),
    PICKING("拣货"),
    COLLECT("集货"),
    REBIN("分播"),
    SHORT_PICKING("缺拣"),
    SHIPPING("出库"),
    MOVE_IN("转入"),
    MOVE_OUT("转出"),
    INVENTORY_PROFIT("盘盈"),
    INVENTORY_LOSS("盘亏"),
    MOVE_CONTAINER("移动容器"),
    INVENTORY_ADJUSTMENT("库存调整"),
    INVENTORY_TRANSFER("库存转移"),
	;

	@Getter
	private String localizedMessage;

	private InventoryBizTypeEnum(String text) {
		this.localizedMessage = text;
    }
}

