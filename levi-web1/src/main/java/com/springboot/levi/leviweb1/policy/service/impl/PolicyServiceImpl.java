package com.springboot.levi.leviweb1.policy.service.impl;

import com.springboot.levi.leviweb1.policy.PolicyDTO;
import com.springboot.levi.leviweb1.policy.service.IPolicyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jianghaihui
 * @date 2021/1/25 16:56
 */
@Service
public class PolicyServiceImpl implements IPolicyService {

    //todo  从数据库中查询出来
    @Override
    public List<PolicyDTO> selectByGroupCode(Long warehouseId, String policyGroupCode) {
        //return DozerBeanUtil.mapList(this.policyManager.selectByGroupCode(warehouseId, policyGroupCode), PolicyDTO.class);
        return null;
    }
}
