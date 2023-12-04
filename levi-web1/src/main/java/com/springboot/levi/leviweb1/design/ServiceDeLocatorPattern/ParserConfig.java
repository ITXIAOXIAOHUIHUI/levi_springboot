package com.springboot.levi.leviweb1.design.ServiceDeLocatorPattern;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2023-02-27 10:25
 */
@Configuration
public class ParserConfig {

    @Bean("parserFacotry")
   public FactoryBean serviceLoacatorFacotrybean(){
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ParserFactory.class);
        return factoryBean;
   }

}
