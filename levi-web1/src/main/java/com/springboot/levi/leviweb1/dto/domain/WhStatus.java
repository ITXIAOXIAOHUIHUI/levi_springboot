package com.springboot.levi.leviweb1.dto.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author jianghaihui
 * @date 2020/11/10 14:44
 */

@TableName("whstatus")
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class WhStatus {

    @TableId(value = "line",type = IdType.NONE)
    private String line;

    private String led1;

    private String led2;

    private String led3;

    private String led4;

    private String led5;

    private String led6;


    private String status;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    private String attr6;

    private int createdAt;

    private int updatedAt;
}
