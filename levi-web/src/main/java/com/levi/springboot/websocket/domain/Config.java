package com.levi.springboot.websocket.domain;

import java.io.Serializable;

/**
 * @author jianghaihui
 * @date 2020/11/17 21:06
 */
public class Config implements Serializable {
    private Integer id;

    /**
     * 工作站code编号
     */
    private String code;

    private String ip;

    private Integer port;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
