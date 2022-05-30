package com.springboot.levi.leviweb1.webservce.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author jianghaihui
 * @date 2020/11/13 15:43
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"msgCode", "message"})
public class WSResponseHeader {

    @XmlElement(name = "msgCode")
    @JsonProperty(value = "msgCode")
    private String msgCode;

    @XmlElement(name = "message")
    @JsonProperty(value = "message")
    private String message;

    public WSResponseHeader() {
        super();
    }

    public WSResponseHeader(String msgCode, String message) {
        super();
        this.msgCode = msgCode;
        this.message = message;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
