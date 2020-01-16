package com.levi.design.pattern.jdk18;

/**
 * @author jianghaihui
 * @date 2020/1/16 11:03
 */
public enum BrokerType {
    /** engine */
    ENGINE("evo-wcs-engine"),
    /** 货到人 */
    G2P("evo-wcs-g2p"),
    /** 料箱到人 */
    W2P("evo-wcs-w2p"),
    /** 包裹 */
    PSS("evo-wcs-ps"),
    /** 标准接口 */
    SI_CARRY("evo-wcs-si"),
    ;

    private final String applicationId;

    BrokerType(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationId() {
        return applicationId;
    }
}
