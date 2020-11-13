package com.levi.java.interview;

/**
 * @author jianghaihui
 * @date 2020/10/15 17:45
 */
public class Father  {

    private String userName;

    private Integer age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Father(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }
}
