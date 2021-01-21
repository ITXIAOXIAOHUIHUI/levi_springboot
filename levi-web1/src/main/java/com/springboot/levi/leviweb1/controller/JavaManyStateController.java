package com.springboot.levi.leviweb1.controller;

import com.springboot.levi.leviweb1.dto.PickingBackDto;
import com.springboot.levi.leviweb1.service.IPickingWorkFeedbackPolicy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author jianghaihui
 * @date 2020/10/14 11:08
 */
@RestController
@RequestMapping(value = "/api/java")
public class JavaManyStateController {
    @Resource
    private Map<String, IPickingWorkFeedbackPolicy> pickingWorkFeedbackPolicyMap;

    @PostMapping(value = "/many/state")
    public Object manyState(@RequestBody PickingBackDto pickingBackDto) {

        String result = pickingWorkFeedbackPolicyMap.get(pickingBackDto.getManyStateEnum().getInstance()).handlePickWorkFeedBack(pickingBackDto);

        return result;
    }

    @PostMapping(value = "/transfer-level3-inventory")
    public Object transferLevel3Inventory(@RequestBody PickingBackDto pickingBackDto) {

        String result = pickingWorkFeedbackPolicyMap.get(pickingBackDto.getManyStateEnum().getInstance()).handlePickWorkFeedBack(pickingBackDto);

        return result;
    }

}
