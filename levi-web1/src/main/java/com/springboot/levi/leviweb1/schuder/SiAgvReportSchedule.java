package com.springboot.levi.leviweb1.schuder;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Introspector;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2023-03-04 00:01
 */
@Service
@Slf4j
public class SiAgvReportSchedule {

    @Resource
    private ApplicationContext applicationContext;
    //key  热度编码   热度对应的区域
    public static Map<String, String> siBucketHotBoMap = Maps.newConcurrentMap();
    @EventListener
    public void init(ApplicationReadyEvent applicationReadyEvent){
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1,
                new ThreadFactoryBuilder().setNameFormat("si-agv-report-%d").build());
        service.scheduleAtFixedRate(this::reportAgvState,0L,2000L, TimeUnit.MILLISECONDS);

    }

    private void reportAgvState() {
        log.info("this is fixed date running");
        try {
            List<Class> allClasses = getAllClassMatchPackagePatten();
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)applicationContext.getAutowireCapableBeanFactory();
            log.info("allclasses :{}",allClasses);
            for(Class clazz :allClasses){
                if(clazz.isAnnotationPresent(ConditionalOnProperty.class)){
                    String simpleName = clazz.getSimpleName();
                    String beanName = Introspector.decapitalize(clazz.getSimpleName());
                    ConditionalOnProperty conditional = (ConditionalOnProperty)clazz.getAnnotation(ConditionalOnProperty.class);

                    String key = conditional.prefix() + conditional.value()[0];
                    String expectedValue = conditional.havingValue();
                    log.info("key :{} having:{}" ,key,expectedValue);
                }
            }

        } catch (IOException e) {
            log.info("aaa");
        }
    }

    private List<Class> getAllClassMatchPackagePatten() throws IOException {
        Set<Class> allClasses =  new HashSet<>();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
        org.springframework.core.io.Resource[] resources = resolver.getResources("classpath*:com/springboot/levi/leviweb1/**/*.class");
        for(org.springframework.core.io.Resource resource : resources){
            MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
            try{
                Class clazz = Class.forName(metadataReader.getClassMetadata().getClassName());
                //如果clazz是不是继承来自另外一个父类（Runnable），一个接口（clazz）是不是实现了另外一个接口（Runnable），
                if (Runnable.class.isAssignableFrom(clazz) && clazz.isAnnotationPresent(ConditionalOnProperty.class)) {
                    allClasses.add(clazz);
                }
            }catch (Exception e){
                log.error("getAllClassMatchPackagePatten error:{}",e);
            }
        }
        log.info("allClasses :{}",allClasses);
        return allClasses.stream().sorted(Comparator.comparingInt(clazz-> StringUtils.isBlank(getThreadExecutorNameFromField(clazz)) ? 0 : 1)).collect(Collectors.toList());
    }
    private String getThreadExecutorNameFromField(Class clazz) {
        for(Field field : clazz.getDeclaredFields()){
            if(TaskExecutor.class.isAssignableFrom(field.getType())){
                String threadExecutorName = null;
                if(field.isAnnotationPresent(Qualifier.class)) {
                    Qualifier qualifier = field.getAnnotation(Qualifier.class);
                    threadExecutorName = qualifier.value();
                } else if(field.isAnnotationPresent(Resource.class)) {
                    Resource resource = field.getAnnotation(Resource.class);
                    threadExecutorName = resource.name();
                }
                return threadExecutorName;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println(Objects.equals("1",null));



    }
}
