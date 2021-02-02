/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.springboot.levi.leviweb1.policy.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component("SpringBeanFactory.WES")
@Slf4j
public class SpringBeanFactory implements ApplicationContextAware, InitializingBean, BeanPostProcessor {
    /**
     * ApplicationContext 对象
     */
    private ApplicationContext applicationContext;

    /**
     * 单子
     */
    private static SpringBeanFactory instance = new SpringBeanFactory();

    public static void setInstance(SpringBeanFactory instance) {
        SpringBeanFactory.instance = instance;
    }

    private static SpringBeanFactory getInstance() {
        return SpringBeanFactory.instance;
    }

    /**
     * 获取服务类对象
     *
     * @param type
     * @return
     */
    public static String[] getBeanDefinitionNames() {
        String[] names = getInstance().applicationContext.getBeanDefinitionNames();
        return names;
    }

    /**
     * 获取服务类对象
     *
     * @param type
     * @return
     */
    public static <T> T getService(Class<T> type) {
        return getBean(type);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return getInstance().applicationContext;
    }

    /**
     * (non-Javadoc)
     *
     * @see InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        SpringBeanFactory.setInstance(this);
        String[] names = this.applicationContext.getBeanDefinitionNames();
        for (int i = 0; i < names.length; i++) {
            log.debug(" == names[" + i + "]:" + names[i]);
        }
    }

    /**
     * (non-Javadoc)
     */
    public <T> T getInnerBean(String name, Class<T> targetClass) {
        return (T) this.applicationContext.getBean(name, targetClass);
    }

    /**
     * (non-Javadoc)
     */
    public Object getBean(String name) {
        return this.applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> type) {
        return instance.getInnerBean(type);
    }

    public static <T> T getBean(String name, Class<T> type) {
        return instance.getInnerBean(name, type);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> targetClass) {
        return instance.applicationContext.getBeansOfType(targetClass);
    }

    /**
     * 根据接口获得实体
     */
    @SuppressWarnings("unchecked")
    private <T> T getInnerBean(Class<T> type) {
        String[] names = this.applicationContext.getBeanNamesForType(type);
        if (names == null || names.length == 0) {
            return null;
        }
        String beanName = names[0];
        for (String name : names) {
            if (StringUtils.equalsIgnoreCase(name, type.getSimpleName())) {
                beanName = name;
                break;
            }
        }
        return (T) this.applicationContext.getBean(beanName);
    }

    /**
     * (non-Javadoc)
     */
    public boolean containsObject(String name) {
        return this.applicationContext.containsBean(name);
    }

    /**
     * (non-Javadoc)
     */
    public void bind(Object target) {
        this.applicationContext.getAutowireCapableBeanFactory().autowireBeanProperties(target, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
