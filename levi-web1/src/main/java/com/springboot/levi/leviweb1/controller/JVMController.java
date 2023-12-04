package com.springboot.levi.leviweb1.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-06 16:15
 */
@RestController
@RequestMapping(value = "/levi/jvm")
@Api(tags = "JVM分析")
@Slf4j
public class JVMController {

    static class OOMObject{
        public  byte[] placeholder = new byte[64*1024];
    }
    @RequestMapping(value = "/memory", method = RequestMethod.GET)
    public void memoryAdustment(@RequestParam("number") int numer) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        for(int i = 0 ;i < numer;i++){
            //稍作延时，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    static class SynAddRunnable implements Runnable{
        int a,b;
        public SynAddRunnable(int a,int b){
            this.a = a;
            this.b = b;
        }
        @Override
        public void run() {
            synchronized (Integer.valueOf(a)){
                synchronized (Integer.valueOf(b)){
                    System.out.println(a+b);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("1111");
        },"testThread").start();

        /*for(int i= 0 ;i < 100;i++){
            System.out.println(i+"");
            new Thread(new SynAddRunnable(1,2)).start();
            new Thread(new SynAddRunnable(2,1)).start();

        }*/
    }
}
