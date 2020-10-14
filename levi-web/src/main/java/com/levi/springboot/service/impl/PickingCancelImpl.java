package com.levi.springboot.service.impl;

import com.levi.springboot.dto.PickingBackDto;
import com.levi.springboot.service.IPickingWorkFeedbackPolicy;
import org.springframework.stereotype.Service;

/**
 * @author jianghaihui
 * @date 2020/10/14 11:21
 */
@Service("PickingCancel")
public class PickingCancelImpl implements IPickingWorkFeedbackPolicy {
    @Override
    public String handlePickWorkFeedBack(PickingBackDto pickingBackDto) {
        System.out.println("******拣货取消了*****");
        return "******拣货取消了*****";
    }
}
