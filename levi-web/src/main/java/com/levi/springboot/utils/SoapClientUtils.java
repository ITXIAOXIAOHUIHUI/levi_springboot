package com.levi.springboot.utils;

import com.levi.springboot.jinjiang.generated.AGVInterfaceServiceSoap;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.web.client.ResourceAccessException;

import java.lang.reflect.Method;

/**
 * @author jianghaihui
 * @date 2020/10/31 18:47
 */
@Slf4j
public class SoapClientUtils {

    public String exchange(String url, String serviceName, String requestEntity, Class<?> responseType)  {
       String  responseEntity = null;
        try {
            String resultJson = callWebservice(url, serviceName, requestEntity);

            log.info("soap  server response :{}    service name :",resultJson, serviceName);
        } catch (ResourceAccessException e) {
            log.error("soap  server response fail :{}    service name :",e, serviceName);
            throw e;
        }

        return null;
    }

    private String callWebservice(String url, String serviceName, String jsonPara) {
       AGVInterfaceServiceSoap myWebService = getInterface(url);
        Class<?> serviceSoapClass = AGVInterfaceServiceSoap.class;

        try {
            Method method = serviceSoapClass.getMethod(serviceName, String.class);
            String resultJson = (String) method.invoke(url, jsonPara);

            return resultJson;
        } catch (Exception e) {
        }

        return null;
    }

    public AGVInterfaceServiceSoap getInterface(String url) {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
       // factoryBean.setServiceClass(AGVInterfaceServiceSoap.class);
        factoryBean.setAddress(url);

        // 请求报文拦截器
        factoryBean.getInInterceptors().add(new LoggingInInterceptor());
        // 响应报文拦截器
        factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
        return (AGVInterfaceServiceSoap) factoryBean.create();
    }
}
