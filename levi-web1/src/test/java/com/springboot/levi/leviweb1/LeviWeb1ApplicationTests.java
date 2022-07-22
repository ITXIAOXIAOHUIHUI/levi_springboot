package com.springboot.levi.leviweb1;

import com.springboot.levi.leviweb1.design.qiaojie.Imessage;
import com.springboot.levi.leviweb1.event.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LeviWeb1ApplicationTests {

    @Resource
    private EventService eventService;
    @Resource
    Map<String, Imessage> map;

    @Resource
    List<Imessage> list;
    @Test
    public void contextLoads() {
    }


    @Test
    public void test1(){
        System.out.println("1111111111");
        System.out.println(map.size());
        System.out.println("222222222");
        System.out.println(list.size());
    }


}
