package com.springboot.levi.leviweb1.policy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jianghaihui
 * @date 2020/9/22 10:53
 */
@Component("lot-compare-dc-sorter")
@Slf4j
public class LotCompareDcSorter implements ILevel2InventorySorter {

    @Resource
    private IPolicyEngineProxy policyEngineProxy;


    @Override
    public int calculateSortNumber(String orderType, Long warehouseId) {
        LotCompareReceivedDate input = new LotCompareReceivedDate();
        input.setOrderType(orderType);
        policyEngineProxy.applyPolicy(warehouseId, "1", input, LotCompareDcPolicyResult.class);

        return 1;
    }


}
