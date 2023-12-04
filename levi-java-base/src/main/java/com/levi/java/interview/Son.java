package com.levi.java.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jianghaihui
 * @date 2020/10/15 17:46
 */

public class Son {

    private String school;
    private String company;

    private Integer age1;
    private int age2;

    private Float hight1;
    private float hight2;

    private Double d1;

    private double d2;


    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getAge1() {
        return age1;
    }

    public void setAge1(Integer age1) {
        this.age1 = age1;
    }

    public int getAge2() {
        return age2;
    }

    public void setAge2(int age2) {
        this.age2 = age2;
    }

    public Float getHight1() {
        return hight1;
    }

    public void setHight1(Float hight1) {
        this.hight1 = hight1;
    }

    public float getHight2() {
        return hight2;
    }

    public void setHight2(float hight2) {
        this.hight2 = hight2;
    }

    public Double getD1() {
        return d1;
    }

    public void setD1(Double d1) {
        this.d1 = d1;
    }

    public double getD2() {
        return d2;
    }

    public void setD2(double d2) {
        this.d2 = d2;
    }

    public static void main(String[] args) {
        List<Son> result = new ArrayList<>();
        for(int i = 0;i< 2500;i++){
            Son son = new Son();
            son.setCompany("aaa"+i);
            result.add(son);
        }
        List<Son> basicAgvPointDOList=new ArrayList<>();
        System.out.println(result.size());
        int size = 1000;
        for(int i=0; i< result.size(); i=i+size) {
            System.out.println("aaaa"+i);
            int toIndex = i + size > result.size() ? result.size() : i + size;
            List<Son> subList = result.subList(i, toIndex);

            basicAgvPointDOList.addAll(subList);
        }
        System.out.println(basicAgvPointDOList.size());
    }
}
