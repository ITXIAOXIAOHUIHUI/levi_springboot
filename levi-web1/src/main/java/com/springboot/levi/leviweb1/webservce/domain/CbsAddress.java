package com.springboot.levi.leviweb1.webservce.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author jianghaihui
 * @date 2020/11/13 16:02
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"CbsAddress" })
public class CbsAddress {

    @XmlElement(name = "CbsAddress")
    private String CbsAddress;

    public String getCbsAddress() {
        return CbsAddress;
    }

    public void setCbsAddress(String cbsAddress) {
        CbsAddress = cbsAddress;
    }

}
