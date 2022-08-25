package com.springboot.levi.leviweb1.model;

import lombok.Data;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-07-22 16:26
 */
@Data
public class ExcelModel {

    @FiledMapper(value = "货位号")
    private String slotCode;
    @FiledMapper(value = "料箱号")
    private String containerCode;
}
