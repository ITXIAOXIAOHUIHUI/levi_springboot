package com.springboot.levi.leviweb1.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.levi.leviweb1.dto.PickingBackDto;
import com.springboot.levi.leviweb1.dto.ReqBody;
import com.springboot.levi.leviweb1.dto.ReqContent;
import com.springboot.levi.leviweb1.model.InventoryLocation;
import com.springboot.levi.leviweb1.service.IPickingWorkFeedbackPolicy;
import com.springboot.levi.leviweb1.utils.HttpUtils;
import com.springboot.levi.leviweb1.wcs.Test;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jianghaihui
 * @date 2020/10/14 11:08
 */
@RestController
@RequestMapping(value = "/api/java")
@Api(value = "测试发送")
@Slf4j
public class JavaManyStateController {
    @Resource
    private Map<String, IPickingWorkFeedbackPolicy> pickingWorkFeedbackPolicyMap;

    @PostMapping(value = "/many/state")
    public Object manyState(@RequestBody PickingBackDto pickingBackDto) {

        String result = pickingWorkFeedbackPolicyMap.get(pickingBackDto.getManyStateEnum().getInstance()).handlePickWorkFeedBack(pickingBackDto);

        return result;
    }


    @PostMapping(value = "/many/test")
    public Object postParam(@RequestParam("sign") String sign, @RequestBody ReqContent reqContent) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result =  HttpUtils.sendHttpPost("http://localhost:8080/ws/platBaseService/invoke/quicktron2sis_inventory_push", "{}");
        log.info("get inventory data:{}",result);
        String userId = reqContent.getRequest().getHeader().getUserId();
        String userKey = reqContent.getRequest().getHeader().getUserKey();
        ReqBody body = reqContent.getRequest().getBody();
        StringBuffer sb = new StringBuffer();
        sb.append(userId).append(userKey).append(body);
        log.info("sb:{}",sb.toString());
        Test test = new Test();
        ReqBody body1 = reqContent.getRequest().getBody();
        String md5Result = SecurityUtil.md5sum(JSON.toJSONString(body1));
        log.info("md5Result:{}",md5Result);
        return md5Result;
    }

    @PostMapping(value = "/transfer-level3-inventory")
    public Object transferLevel3Inventory(@RequestBody PickingBackDto pickingBackDto) {

        String result = pickingWorkFeedbackPolicyMap.get(pickingBackDto.getManyStateEnum().getInstance()).handlePickWorkFeedBack(pickingBackDto);

        return result;
    }


    @PostMapping(value = "/sendSecurity")
    @ApiOperation("发送数据")
    public Object sendSecurity() throws Exception {
        List<InventoryLocation> list = new ArrayList<>();
        for(int i = 0 ;i < 10;i++){
            InventoryLocation  location = new InventoryLocation();
            location.setBucketCode(i+"bucketCode");
            location.setBucketSlotCode(i+"bucketSlotCode");
            location.setLevel1ContainerCode(i+"level1ContainerCode");
            location.setVirtual(true);
            location.setZoneCode(i+"zoneCode");
            list.add(location);
        }
        String request = JSON.toJSONString(list);
        String  url = "http://localhost:10080/api/device/sendBody";
        String result = HttpUtils.sendHttpPost(url, request);
        return result;
    }





    public static void performTask(int value) {
        System.out.println("Performing task with value: " + value);
    }
}
