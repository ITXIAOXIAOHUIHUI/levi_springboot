package com.springboot.levi.leviweb1.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2023-08-29 17:28
 */
@Data
@Accessors(chain = true)
public class SiCancelJobDto {
    /**
     * 任务编号
     */
    @NotBlank(message = "ERR_ILLEGAL_RJI")
    private String robotJobId;
    /**
     * 仓库
     */
    @NotNull(message = "ERR_ILLEGAL_WHI")
    private Long warehouseId;

    /**
     * 执行模式
     */
    private String executeMode;
    /**
     * 取消原因
     */
    private String reason;

    private String targetSlotCode;

    /**
     * 任务组取消，给纯料箱用的
     */
    private String robotJobGroupId ;
}
