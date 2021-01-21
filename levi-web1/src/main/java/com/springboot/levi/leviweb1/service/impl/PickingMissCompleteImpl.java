package com.springboot.levi.leviweb1.service.impl;

import com.springboot.levi.leviweb1.dto.PickingBackDto;
import com.springboot.levi.leviweb1.service.IPickingWorkFeedbackPolicy;
import org.springframework.stereotype.Service;

/**
 * @author jianghaihui
 * @date 2020/10/14 11:21
 */
@Service("PickingMissComplete")
public class PickingMissCompleteImpl implements IPickingWorkFeedbackPolicy {
    @Override
    public String handlePickWorkFeedBack(PickingBackDto pickingBackDto) {
        System.out.println("*******缺拣完成了******");
        return "*******缺拣完成了******  PickingMissComplete";
    }
}
