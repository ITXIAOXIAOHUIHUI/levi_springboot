package com.levi.springboot.service;

import com.levi.springboot.dto.PickingBackDto;

/**
 * @author jianghaihui
 * @date 2020/10/14 11:15
 */
public interface IPickingWorkFeedbackPolicy {

    String handlePickWorkFeedBack(PickingBackDto pickingBackDto);
}
