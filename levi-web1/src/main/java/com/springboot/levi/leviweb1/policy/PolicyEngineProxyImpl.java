package com.springboot.levi.leviweb1.policy;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jianghaihui
 * @date 2021/1/25 16:26
 */
@Component
public class PolicyEngineProxyImpl implements IPolicyEngineProxy {

    @Resource
    private IPolicyEngine policyEngine;

    @Override
    public <T> Object applyPolicy(Long warehouseId, String policyCode, Object inputObj, Class<T> resultType) {
        // PolicyDTO policyDTO = policyEngine.applyPolicy(warehouseId, policyCode, inputObj);
        //return PolicyUtil.convertResult(policyDTO, resultType);
        return null;
    }
}
