package com.springboot.levi.leviweb1.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SIOperationDto {

    private String robotJobId;

    @NotNull(message = "ERR_ILLEGAL_WHI")
    private Long warehouseId;

    private String bucketCode;

    private String alias;

    private String bucketSlotCode;

    private String jobId;

    //货架是否捡空
    private boolean nullFlag;
}

