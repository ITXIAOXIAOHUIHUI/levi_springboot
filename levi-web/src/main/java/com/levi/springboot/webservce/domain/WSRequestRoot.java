package com.levi.springboot.webservce.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author jianghaihui
 * @date 2020/11/13 15:42
 */
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"service"})
public class WSRequestRoot {


    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
