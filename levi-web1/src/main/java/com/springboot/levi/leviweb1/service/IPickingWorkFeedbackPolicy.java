package com.springboot.levi.leviweb1.service;

import com.springboot.levi.leviweb1.dto.PickingBackDto;

/**
 * @author jianghaihui
 * @date 2020/10/14 11:15
 */
public interface IPickingWorkFeedbackPolicy {

    String handlePickWorkFeedBack(PickingBackDto pickingBackDto);
}
