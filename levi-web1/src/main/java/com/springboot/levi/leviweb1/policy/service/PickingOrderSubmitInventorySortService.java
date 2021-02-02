package com.springboot.levi.leviweb1.policy.service;

import com.google.common.collect.Maps;
import com.springboot.levi.leviweb1.policy.ILevel2InventorySorter;
import com.springboot.levi.leviweb1.policy.PolicyDTO;
import com.springboot.levi.leviweb1.policy.utils.SpringBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jianghaihui
 * @date 2021/1/25 16:52
 */
@Slf4j
@Service
public class PickingOrderSubmitInventorySortService {

    private String formatKey = "%s_%s";

    @Resource
    private IPolicyService policyService;

    private Map<String, List<String>> orderTypeWithPolicyCode = Maps.newHashMap();

    public void test() {
        //todo
        List<String> sortPolicyCode = getSortPolicyCode("bs", 1L);
        if (CollectionUtils.isEmpty(sortPolicyCode)) {
        }


        /**TODO
         * 暂时想到最简单的排序计算
         * 中间的优化比如对于一些规则做缓存
         * 和避免多次去查库区
         */
        for (String s : sortPolicyCode) {
            ILevel2InventorySorter sorter = SpringBeanFactory.getBean(s, ILevel2InventorySorter.class);
            //ret = sorter.calculateSortNumber(a, b, orderType, warehouseId);
            //if (ret != 0) {
            //   break;
            //}
        }
    }


    private List<String> getSortPolicyCode(String orderType, Long warehouseId) {
        String format = String.format(formatKey, warehouseId, orderType);
        List<String> policyCodes = orderTypeWithPolicyCode.get(format);
        if (CollectionUtils.isEmpty(policyCodes)) {
            List<PolicyDTO> policyDTOS = policyService.selectByGroupCode(warehouseId, "test1");
            if (CollectionUtils.isEmpty(policyDTOS)) {
                // new WesPickingException(WesPickingErrorCode.POLICY_GROUP_CAN_NOT_FIND_POLICY.getCode(),WesPickingErrorCode.POLICY_GROUP_CAN_NOT_FIND_POLICY.getDesc(),PickingPolicyCodes.LEVEL2_INVENTORY_SORT_GROUP_CODE);
            }
            policyCodes = policyDTOS.stream().map(PolicyDTO::getPolicyCode).collect(Collectors.toList());
            orderTypeWithPolicyCode.putIfAbsent(format, policyCodes);
        }
        return policyCodes;
    }
}
