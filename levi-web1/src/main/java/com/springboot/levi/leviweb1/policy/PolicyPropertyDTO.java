package com.springboot.levi.leviweb1.policy;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author jianghaihui
 * @date 2021/1/25 16:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class PolicyPropertyDTO extends BaseRequestVO {


    @ApiModelProperty(value = "对象类型ID", name = "objectClassId", example = "1")
    @NotNull
    private Long objectClassId;

    @ApiModelProperty(value = "属性名称", name = "propertyName", example = "priorityLevel")
    @NotBlank
    @Length(max = 50)
    private String propertyName;

    /**
     * 属性值
     */
    private String propertyValue;

    /**
     * 枚举值
     */
    private List<DictionaryItemDTO> choiceValues;

    @ApiModelProperty(value = "属性描述", name = "propertyDesc", example = "优先级等级")
    @Length(max = 500)
    private String propertyDesc;

    @ApiModelProperty(value = "属性数据类型", name = "dataType", example = "String")
    @NotBlank
    @Length(max = 20)
    private String dataType;

    @ApiModelProperty(value = "属性对象类型", name = "dataObjectClass", example = "")
//	@NotBlank
//	@Length(max = 50)
    private String dataObjectClass;

    @ApiModelProperty(value = "枚举值标记", name = "choiceFlag", example = "1")
    @NotNull
    private Boolean choiceFlag;

    @ApiModelProperty(value = "多值标记", name = "multipleFlag", example = "0")
    @NotNull
    private Boolean multipleFlag;

    @ApiModelProperty(value = "排序值", name = "sortNum", example = "1")
//	@NotNull
    private Integer sortNum;
}
