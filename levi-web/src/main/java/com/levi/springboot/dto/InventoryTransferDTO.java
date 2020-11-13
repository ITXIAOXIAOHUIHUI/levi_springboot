/******************************************************************************
 * Copyright (C) 2019 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 * 版权所有 翻版必究
 *****************************************************************************/
package com.levi.springboot.dto;

import com.levi.springboot.enums.InventoryBizTypeEnum;
import com.levi.springboot.model.InventoryLocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 三级库存转移DTO
 *
 * @author chengjinzhi
 * @history 2019-3-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryTransferDTO {

	/** 业务类型 */
	//@NotNull
	private InventoryBizTypeEnum bizType;

	//@NotNull
	private String bizIdempotentId;

	/** 业务单据ID */
	private Long bizBillId;

	/** 业务单据明细ID */
	private Long bizBillDetailId;

	//@NotNull
	private Long fromLevel3InventoryId;

	/** 转移数量  为null表示全部  */
	private Integer quantity;

	/** 出库预占库存一起转移 */
	private boolean withOutLocked;

	//@NotNull
	//@Valid
	private InventoryLocation toLocation;
}
