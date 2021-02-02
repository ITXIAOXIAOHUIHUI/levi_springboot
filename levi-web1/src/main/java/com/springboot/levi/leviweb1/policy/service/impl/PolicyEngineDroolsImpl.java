package com.springboot.levi.leviweb1.policy.service.impl;

import com.springboot.levi.leviweb1.policy.IPolicyEngine;
import com.springboot.levi.leviweb1.policy.PolicyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianghaihui
 * @date 2021/1/25 17:02
 */
@Slf4j
@Component("PolicyEngineDroolsImpl")
public class PolicyEngineDroolsImpl implements IPolicyEngine {

    @SuppressWarnings({ "unchecked" })
    public PolicyDTO applyPolicy(Long warehouseId, String policyCode, Object inputObj) {
        return null;
        /*if (inputObj == null) {
            //throw new WesException(WesErrorCode.METHOD_ARGUMENT_CANNOT_NULL, "inputObj");
        }
        PolicyDTO policyDTO = policyCache.get(policyCode);
        if (policyDTO == null) {
            policyDTO = policyService.selectOneWithRuleByCode(warehouseId, policyCode);
            if (policyDTO == null) {
                throw new WesException(PolicyErrorCode.POLICY_NOT_FOUND, policyCode);
            }
            this.cachePolicy(policyDTO);
        }
        this.validateInput(policyDTO, inputObj);
        KieBase kieBase = kieBaseCache.get(policyCode);
        StatelessKieSession kSession = kieBase.newStatelessKieSession();

        List<Command<?>> commands = new ArrayList<>();
        BatchExecutionCommand batchCommand = CommandFactory.newBatchExecution(commands);
        Object input = this.convertInput(policyDTO, inputObj);
        //drools.input: {orderType=CS}
        //log.info("drools.input: " + input);
        commands.add(CommandFactory.newInsert(new Pair<Object, Object>(input, null), RESULT_IDENTIFIER));
        try {
            ExecutionResults results = kSession.execute(batchCommand);
            Object resultObj = ((Pair<Object, Object>) results.getValue(RESULT_IDENTIFIER)).getSecond();
            if (resultObj == null) {
                policyDTO.setResult(null);
                return policyDTO;
            }
            this.validateResult(policyDTO, resultObj);
            policyDTO.setResult(resultObj);
        } catch (Exception e) {
            log.error("policy apply error, auto reload policy-'" + policyCode + "'...", e);
            this.reloadPolicy(warehouseId, policyCode);
        }
        return policyDTO;*/
    }

}
