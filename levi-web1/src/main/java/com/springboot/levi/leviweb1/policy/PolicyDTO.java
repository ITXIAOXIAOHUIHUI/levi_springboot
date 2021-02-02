package com.springboot.levi.leviweb1.policy;

import com.springboot.levi.leviweb1.policy.enums.PolicyDataType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author jianghaihui
 * @date 2021/1/25 16:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class PolicyDTO {

    @Valid
    private List<PolicyRuleDTO> policyRules;

    private List<PolicyPropertyDTO> policyConditionPropertyDTOS;

    private List<PolicyPropertyDTO> policyResultPropertyDTOS;

    @ApiModelProperty(value = "策略类别ID", name = "categoryId", example = "3")
    @NotNull
    private Long categoryId;

    @ApiModelProperty(value = "策略代码", name = "policyCode", example = "picking-order-priority")
    @NotBlank
    @Length(max = 50)
    private String policyCode;

    @ApiModelProperty(value = "策略名称", name = "policyName", example = "出库单优先级配置")
    @NotBlank
    @Length(max = 50)
    private String policyName;

    @ApiModelProperty(value = "策略描述", name = "policyDesc", example = "出库单优先级配置")
    @Length(max = 255)
    private String policyDesc;

    @ApiModelProperty(value = "输入数据类型", name = "inputDataType", example = "Object")
    @NotNull
    private PolicyDataType inputDataType;

    /**
     * 解析为人可以直观看懂的字串
     */
    private String inputDataStr;

    @ApiModelProperty(value = "输入对象类型", name = "inputObjectClass", example = "PickingOrderDTO")
    @NotBlank
    @Length(max = 50)
    private String inputObjectClass;

    @ApiModelProperty(value = "输入多值标记", name = "inputMultipleFlag", example = "0")
//	@NotNull
    private Boolean inputMultipleFlag;

    @ApiModelProperty(value = "结论数据类型", name = "resultDataType", example = "Object")
    @NotNull
    private PolicyDataType resultDataType;

    @ApiModelProperty(value = "结论对象类型", name = "resultObjectClass", example = "PickingOrderPriorityPolicyResult")
    @NotBlank
    @Length(max = 50)
    private String resultObjectClass;

    @ApiModelProperty(value = "结论多值标记", name = "resultMultipleFlag", example = "0")
//	@NotNull
    private Boolean resultMultipleFlag;

    @ApiModelProperty(value = "启用标记", name = "enabled", example = "1")
//	@NotNull
    private Boolean enabled;

    @ApiModelProperty(value = "结论", name = "result", example = "")
    private Object result;
}
