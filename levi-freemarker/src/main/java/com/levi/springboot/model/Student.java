package com.levi.springboot.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author jianghaihui
 * @date 2019/9/10 19:29
 */
@Data
@ToString
public class Student {
    private String name;
    private Integer age;
    private Date birthday;
    private Float money;
    private List<Student> friends;//朋友列表
    private Student bestFriend;//最好的朋友
}
