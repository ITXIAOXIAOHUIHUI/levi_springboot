package com.levi.springboot.cms.controller;

import lombok.Data;

/**
 * @author jianghaihui
 * @date 2019/10/26 14:10
 */
@Data
public class Student {
    /**
     * 学号
     */
    private long id;

    private String name;

    private int age;

    /**
     * 年级
     */
    private int grade;

    /**
     * 专业
     */
    private String major;

    /**
     * 学校
     */
    private String school;

    public Student(long id, String name, int age, int grade, String major, String school) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.major = major;
        this.school = school;
    }
}
