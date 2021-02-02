/******************************************************************************
 * Copyright (C) 2019 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 * 版权所有 翻版必究
 *****************************************************************************/
package com.springboot.levi.leviweb1.policy;


import com.springboot.levi.leviweb1.policy.enums.PolicyDataType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author jianghaihui
 * @date 2021/1/25 16:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class PolicyRuleDTO extends BaseRequestVO {

    @ApiModelProperty(value = "所属策略", name = "policyId", example = "1")
    @NotNull
    private Long policyId;

    @ApiModelProperty(value = "策略规则名称", name = "ruleName", example = "出库单优先级配置规则1")
    @NotBlank
    @Length(max = 50)
    private String ruleName;

    @ApiModelProperty(value = "条件", name = "condition", example = "\"[{\"logicOp\":\"AND\",\"lb\":\"\",\"prop\":\"ownerCode\",\"op\":\"EQ\",\"value\":\"A\",\"rb\":\"\"},{\"logicOp\":\"AND\",\"lb\":\"\",\"prop\":\"orderType\",\"op\":\"EQ\",\"value\":\"PUTDOWN\",\"rb\":\"\"}]\"")
    @NotBlank
    @Length(max = 2000)
    private String conditions;
    /**
     * 翻译后可以直观看懂的规则字串
     */
    private String conditionsStr;

    @ApiModelProperty(value = "结论", name = "result", example = "\"{\"priorityLevel\":\"normal\",\"priorityNum\":1}\"")
    @NotBlank
    @Length(max = 2000)

    private String result;
    /**
     * 翻译后可以直观看懂的结果字串
     */
    private String resultStr;

    /**
     * 规则输出是否多指，配合前端添加
     */
    private Boolean resultMultipleFlag;

    /**
     * 结论数据类型
     */
    private PolicyDataType resultDataType;

    @ApiModelProperty(value = "排序值", name = "sortNum", example = "1")
//	@NotNull
    private Integer sortNum;

    @ApiModelProperty(value = "启用标记", name = "enabled", example = "1")
//	@NotNull
    private Boolean enabled;
}
