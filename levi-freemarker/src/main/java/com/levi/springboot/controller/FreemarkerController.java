package com.levi.springboot.controller;

import com.levi.springboot.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author jianghaihui
 * @date 2019/9/10 19:33
 */
@RequestMapping("/freemarker")
@Controller
public class FreemarkerController {


    @RequestMapping("/test1")
    public String freemarker(Map<String, Object> map) {
        map.put("name", "levi");
        //返回模板文件名称
        return "test1";
    }

    @RequestMapping("/test2")
    public String freemarkerTwo(Map<String, Object> map) {
        //向数据模型放数据
        map.put("name", "黑马程序员");
        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setAge(19);
// stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
//向数据模型放数据
        map.put("stus", stus);
//准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
//向数据模型放数据
        map.put("stu1", stu1);
//向数据模型放数据
        map.put("stuMap", stuMap);
//返回模板文件名称
        return "test2";

    }

    @RequestMapping("/test3")
    public String freemarkerThree(Map<String, Object> map) {
        //向数据模型放数据
        map.put("name", "黑马程序员");
        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setAge(19);
// stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
//向数据模型放数据
        map.put("stus", stus);
//准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
//向数据模型放数据
        map.put("stu1", stu1);
//向数据模型放数据
        map.put("stuMap", stuMap);
//返回模板文件名称
        return "test3";

    }
}
