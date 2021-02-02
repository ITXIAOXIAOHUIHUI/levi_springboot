package com.springboot.levi.leviweb1.policy;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
public class BaseDTO {
	@ApiModelProperty(value="ID",name="id")
    private Long id;

    @ApiModelProperty(value="仓库ID",name="warehouseId")
    private Long warehouseId;

    @ApiModelProperty(value="创建时间",name="createdDate")
    private Date createdDate;

    @ApiModelProperty(value="创建人",name="createdUser")
    private String createdUser;

    @ApiModelProperty(value="创建应用",name="createdApp")
    private String createdApp;

    @ApiModelProperty(value="最后修改时间",name="lastUpdatedDate")
    private Date lastUpdatedDate;

    @ApiModelProperty(value="最后修改人",name="lastUpdatedUser")
    private String lastUpdatedUser;

    @ApiModelProperty(value="最后修改应用",name="lastUpdatedApp")
    private String lastUpdatedApp;
}
