package com.springboot.levi.leviweb1.policy;

/**
 * @author jianghaihui
 * @date 2021/1/25 16:25
 */
public interface IPolicyEngineProxy {

    /**
     * 应用策略
     *
     * @param warehouseId 仓库ID
     * @param policyCode  策略代码
     * @param input       策略输入对象
     * @param resultType  返回值类型（当resultDataType为Object时有效）
     * @return
     */
    public <T> Object applyPolicy(Long warehouseId, String policyCode, Object input, Class<T> resultType);
}
