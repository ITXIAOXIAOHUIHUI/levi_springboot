package com.springboot.levi.leviweb1.dto.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.*;

import java.util.Date;

/**
 * @author jianghaihui
 * @date 2021/1/10 23:40
 */
@TableName("user")
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String userId;
    private String userName;
    private String email;

    // 字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date gmt_create;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmt_modified;

    @Version //乐观锁Version注解
    private Integer version;
}
