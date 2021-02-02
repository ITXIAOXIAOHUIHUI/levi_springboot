package com.springboot.levi.leviweb1.policy;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author jianghaihui
 * @date 2021/1/25 16:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictionaryItemDTO  extends BaseDTO  {

    @ApiModelProperty(value = "字典ID", name = "dictionaryId")
    private Long dictionaryId;

    private String dictionaryCode;
    private String dictionaryName;

    private String ownerCode;

    @ApiModelProperty(value = "字典键", name = "itemKey")
    @NotBlank
    @Length(max = 50)
    private String itemKey;

    @ApiModelProperty(value = "字典值", name = "itemValue")
    @NotNull
    @Length(max = 50)
    private String itemValue;

    @ApiModelProperty(value = "字典描述", name = "itemDesc")
    @Length(max = 255)
    private String itemDesc;

    @ApiModelProperty(value = "启用标记", name = "enabled")
    @NotNull
    private Boolean enabled;

    @ApiModelProperty(value = "排序字段", name = "sortNum")
    @NotNull
    private Integer sortNum;

    @ApiModelProperty(value = "上级ItemID", name = "parentId")
    private Long parentItemId;

    private String parentItemCode;
    private String parentItemName;

//	@ApiModelProperty(value = "删除标记", name = "deleteFlag")
//	@NotNull
//	private Boolean deleteFlag;

    public List<DictionaryItemDTO> childItems;

    public List<DictionaryItemDTO> getChildItems(){
        if(childItems == null) {
            childItems = Lists.newArrayList();
        }
        return childItems;
    }

    public void appendChild(DictionaryItemDTO child) {
        if(child != null) {
            this.getChildItems().add(child);
        }
    }
}
