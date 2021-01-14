package com.levi.springboot.wcs;

import lombok.Data;

/**
 * @author jianghaihui
 * @date 2021/1/5 16:19
 */
@Data
public class ConfigDto {

    private Long id;
    private String module;
    private String configGroup;
    private Long warehouseId;
    private String groupInstanceId;
    private String os;
    private Integer hierarchy;
    private String directory;
    private String subDirectory;
    private Integer sequence;
    private String name;
    private String displayName;
    private String value;
    private String valueType;
    private String valueEnum;
    private Integer nonRealTime;
    private String remark;
}
