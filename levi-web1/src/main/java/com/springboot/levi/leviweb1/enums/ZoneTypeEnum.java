/******************************************************************************
 * Copyright (C) 2019 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.springboot.levi.leviweb1.enums;

import lombok.Getter;


public enum ZoneTypeEnum  {
	STORAGE_AGV("AGV存货", false),
	STORAGE_MANUAL("人工存货", false),
	STATION("工作站", false),
	INVENTORY_DIFF("库存差异", true),
	TEMPORARY_DELIVERY("发货暂存", true),
	TEMPORARY_COLLECT("集货暂存", true),
	TEMPORARY_RECEIVING("收货暂存", true),
	TEMPORARY_RETURN("返库暂存", true),
	;

	@Getter
	private String localizedMessage;

	@Getter
	private boolean virtual;

	private ZoneTypeEnum(String text, boolean virtual){
		this.localizedMessage = text;
		this.virtual = virtual;
	}
}

