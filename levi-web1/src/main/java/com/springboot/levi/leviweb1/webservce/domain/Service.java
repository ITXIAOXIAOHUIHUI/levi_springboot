package com.springboot.levi.leviweb1.webservce.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author jianghaihui
 * @date 2020/11/13 15:58
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"serviceId","params"})
public class Service {

    @XmlElement(name = "serviceId")
    @JsonProperty(value = "serviceId")
    private String serviceId;

    @XmlElement(name = "params")
    @JsonProperty(value = "params")
    private CbsAddress  params;


    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public CbsAddress getParams() {
        return params;
    }

    public void setParams(CbsAddress params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId='" + serviceId + '\'' +
                ", params=" + params +
                '}';
    }
}
