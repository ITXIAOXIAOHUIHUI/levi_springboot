package com.levi.springboot.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jianghaihui
 * @date 2020/11/13 23:29
 */
@Data
@Slf4j
public class Test {

    private String led1;

    private String lea2;


    public static void main(String[] args) {
        List<Test> list = new ArrayList<>();
        Test test = new Test();
        test.setLed1("1");
        test.setLea2("2");
        list.add(test);

        String  str = "[ {\n" +
                "  \"line\" : \"L001\",\n" +
                "  \"led1\" : \"1\",\n" +
                "  \"led2\" : \"0\",\n" +
                "  \"led3\" : \"0\",\n" +
                "  \"led4\" : \"0\",\n" +
                "  \"led5\" : \"0\",\n" +
                "  \"led6\" : \"0\",\n" +
                "  \"led7\" : \"0\",\n" +
                "  \"status\" : \"0\",\n" +
                "  \"attr1\" : \"\",\n" +
                "  \"attr2\" : \"\",\n" +
                "  \"attr3\" : \"\",\n" +
                "  \"attr4\" : \"\",\n" +
                "  \"attr5\" : \"\",\n" +
                "  \"attr6\" : \"\",\n" +
                "  \"createdAt\" : 0,\n" +
                "  \"updatedAt\" : 0\n" +
                "}, {\n" +
                "  \"line\" : \"L002\",\n" +
                "  \"led1\" : \"1\",\n" +
                "  \"led2\" : \"0\",\n" +
                "  \"led3\" : \"0\",\n" +
                "  \"led4\" : \"0\",\n" +
                "  \"led5\" : \"0\",\n" +
                "  \"led6\" : \"0\",\n" +
                "  \"led7\" : \"0\",\n" +
                "  \"status\" : \"0\",\n" +
                "  \"attr1\" : \"\",\n" +
                "  \"attr2\" : \"\",\n" +
                "  \"attr3\" : \"\",\n" +
                "  \"attr4\" : \"\",\n" +
                "  \"attr5\" : \"\",\n" +
                "  \"attr6\" : \"\",\n" +
                "  \"createdAt\" : 0,\n" +
                "  \"updatedAt\" : 0\n" +
                "}, {\n" +
                "  \"line\" : \"L003\",\n" +
                "  \"led1\" : \"1\",\n" +
                "  \"led2\" : \"0\",\n" +
                "  \"led3\" : \"0\",\n" +
                "  \"led4\" : \"0\",\n" +
                "  \"led5\" : \"0\",\n" +
                "  \"led6\" : \"0\",\n" +
                "  \"led7\" : \"0\",\n" +
                "  \"status\" : \"0\",\n" +
                "  \"attr1\" : \"\",\n" +
                "  \"attr2\" : \"\",\n" +
                "  \"attr3\" : \"\",\n" +
                "  \"attr4\" : \"\",\n" +
                "  \"attr5\" : \"\",\n" +
                "  \"attr6\" : \"\",\n" +
                "  \"createdAt\" : 0,\n" +
                "  \"updatedAt\" : 0\n" +
                "}, {\n" +
                "  \"line\" : \"L004\",\n" +
                "  \"led1\" : \"1\",\n" +
                "  \"led2\" : \"0\",\n" +
                "  \"led3\" : \"0\",\n" +
                "  \"led4\" : \"0\",\n" +
                "  \"led5\" : \"0\",\n" +
                "  \"led6\" : \"0\",\n" +
                "  \"led7\" : \"0\",\n" +
                "  \"status\" : \"0\",\n" +
                "  \"attr1\" : \"\",\n" +
                "  \"attr2\" : \"\",\n" +
                "  \"attr3\" : \"\",\n" +
                "  \"attr4\" : \"\",\n" +
                "  \"attr5\" : \"\",\n" +
                "  \"attr6\" : \"\",\n" +
                "  \"createdAt\" : 0,\n" +
                "  \"updatedAt\" : 0\n" +
                "}, {\n" +
                "  \"line\" : \"L005\",\n" +
                "  \"led1\" : \"1\",\n" +
                "  \"led2\" : \"0\",\n" +
                "  \"led3\" : \"0\",\n" +
                "  \"led4\" : \"0\",\n" +
                "  \"led5\" : \"0\",\n" +
                "  \"led6\" : \"0\",\n" +
                "  \"led7\" : \"0\",\n" +
                "  \"status\" : \"0\",\n" +
                "  \"attr1\" : \"\",\n" +
                "  \"attr2\" : \"\",\n" +
                "  \"attr3\" : \"\",\n" +
                "  \"attr4\" : \"\",\n" +
                "  \"attr5\" : \"\",\n" +
                "  \"attr6\" : \"\",\n" +
                "  \"createdAt\" : 0,\n" +
                "  \"updatedAt\" : 0\n" +
                "}, {\n" +
                "  \"line\" : \"L006\",\n" +
                "  \"led1\" : \"1\",\n" +
                "  \"led2\" : \"0\",\n" +
                "  \"led3\" : \"0\",\n" +
                "  \"led4\" : \"0\",\n" +
                "  \"led5\" : \"0\",\n" +
                "  \"led6\" : \"0\",\n" +
                "  \"led7\" : \"0\",\n" +
                "  \"status\" : \"0\",\n" +
                "  \"attr1\" : \"\",\n" +
                "  \"attr2\" : \"\",\n" +
                "  \"attr3\" : \"\",\n" +
                "  \"attr4\" : \"\",\n" +
                "  \"attr5\" : \"\",\n" +
                "  \"attr6\" : \"\",\n" +
                "  \"createdAt\" : 0,\n" +
                "  \"updatedAt\" : 0\n" +
                "}, {\n" +
                "  \"line\" : \"L007\",\n" +
                "  \"led1\" : \"1\",\n" +
                "  \"led2\" : \"0\",\n" +
                "  \"led3\" : \"0\",\n" +
                "  \"led4\" : \"0\",\n" +
                "  \"led5\" : \"0\",\n" +
                "  \"led6\" : \"0\",\n" +
                "  \"led7\" : \"0\",\n" +
                "  \"status\" : \"0\",\n" +
                "  \"attr1\" : \"\",\n" +
                "  \"attr2\" : \"\",\n" +
                "  \"attr3\" : \"\",\n" +
                "  \"attr4\" : \"\",\n" +
                "  \"attr5\" : \"\",\n" +
                "  \"attr6\" : \"\",\n" +
                "  \"createdAt\" : 0,\n" +
                "  \"updatedAt\" : 0\n" +
                "} ]\n";


        Map<String, Test> collect = list.stream().collect(Collectors.toMap(t -> t.getLed1(), t -> t));
        for(String key : collect.keySet()){
            System.out.println(key);
            Test test1 = collect.get(key);
            log.info("test1 :{}",test1.toString());
            System.out.println(test1.getLea2() instanceof  String);
            if("1".equals("1")){
                System.out.println("+++++");
            }
        }
        System.out.println();

    }
}
