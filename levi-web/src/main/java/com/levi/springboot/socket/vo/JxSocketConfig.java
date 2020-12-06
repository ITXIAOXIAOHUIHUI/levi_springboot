package com.levi.springboot.socket.vo;

/**
 * @author jianghaihui
 * @date 2020/11/19 14:00
 */

public class JxSocketConfig {


    private Long id;

    private String ledIp;

    private int port;

    private String ledNo;

    private String status;

    private String udf1;

    public JxSocketConfig(String ledIp, int port) {
        this.ledIp = ledIp;
        this.port = port;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLedIp() {
        return ledIp;
    }

    public void setLedIp(String ledIp) {
        this.ledIp = ledIp;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getLedNo() {
        return ledNo;
    }

    public void setLedNo(String ledNo) {
        this.ledNo = ledNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUdf1() {
        return udf1;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }
}
