package com.levi.springboot.webservce.service;


import com.levi.springboot.webservce.domain.User;
import com.levi.springboot.webservce.domain.WSRequestRoot;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;


@WebService
public interface UserService {

    @WebMethod//标注该方法为webservice暴露的方法,用于向外公布，它修饰的方法是webservice方法，去掉也没影响的，类似一个注释信息。
    public User getUser(@WebParam(name = "userId") String userId);

    @WebMethod
    @RequestWrapper(localName = "SendCbsAddress", targetNamespace = "http://route.webservice.gateway.veh.me.evun.cn")
    public String SendCbsAddress(@WebParam(name = "requestXML")  WSRequestRoot  requestXML);

    /**
     * 构造上游的请求报文结构
     * @param
     * @return
     */
    @WebMethod
    @RequestWrapper(localName = "SendCbsStatus", targetNamespace = "http://route.webservice.gateway.veh.me.evun.cn")
    public String SendCbsStatus(@WebParam(name = "jsonPara") String  jsonPara);

}
