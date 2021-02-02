package com.springboot.levi.leviweb1.policy.service;

import com.springboot.levi.leviweb1.policy.PolicyDTO;

import java.util.List;

/**
 * @author jianghaihui
 * @date 2021/1/25 16:55
 */
public interface IPolicyService {

    List<PolicyDTO> selectByGroupCode(Long warehouseId, String policyGroupCode);
}
