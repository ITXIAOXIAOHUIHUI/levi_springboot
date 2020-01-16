package com.levi.springboot.email.mapper;

/**
 * @author jianghaihui
 * @date 2020/1/10 16:58
 */
public class EmailInfo {

    private String receiver;

    private Long id;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
