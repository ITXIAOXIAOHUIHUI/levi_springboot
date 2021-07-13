package com.springboot.levi.leviweb1.utils;

import java.util.List;

public class WorkMode {
    public static final String G2P_PICKING = "G2P_PICKING.enabled";
    public static final String G2P_GUIDED_PUTAWAY = "G2P_GUIDED_PUTAWAY.enabled";
    public static final String G2P_DIRECT_PUTAWAY = "G2P_DIRECT_PUTAWAY.enabled";
    public static final String G2P_COUNTCHECK = "G2P_COUNTCHECK.enabled";
    public static final String SI_CARRY = "SI_CARRY.enabled";
    public static final String SI_ROLLER = "SI_ROLLER.enabled";
    public static final String SI_QUICKPICK = "SI_QUICKPICK.enabled";
    public static final String SI_LIFT = "SI_LIFT.enabled";
    public static final String SI_HDS = "SI_HDS.enabled";
    public static final String SI_BULL_HDS = "SI_BULL_HDS.enabled";
    public static final String TALLY = "TALLY.enabled";
    public static final String BUCKET_CONVEY = "BUCKET_CONVEY.enabled";
    public static final String SMALL_PARCEL = "SMALL_PARCEL.enabled";
    public static final String SI_WORKBIN = "SI_WORKBIN.enabled";
    public static final String SI_FORK = "SI_FORK.enabled";
    public static final String SI_REPRINT = "SI_REPRINT.enabled";

    public static final String W2P_PICKING = "W2P_PICKING.enabled";
    public static final String W2P_DIRECT_PUTAWAY = "W2P_DIRECT_PUTAWAY.enabled";
    public static final String W2P_GUIDED_PUTAWAY = "W2P_GUIDED_PUTAWAY.enabled";
    public static final String W2P_COUNTCHECK = "W2P_COUNTCHECK.enabled";
    public static final String VALIDATE_BUCKET = "VALIDATE_BUCKET.enabled";

    public static final String v_HDS_BOOTSTRAP = "SI_HDS_BOOTSTRAP.enabled";

    /**
     * 经典货到人模式,这种作业模式需要wes和工作站的支持
     */
    public static final String V_CLASSIC_G2P = "CLASSIC_G2P.enabled";

    /**
     * 只要货到人业务启动了将其设置为启用
     */
    public static final String v_G2p = "G2p.enabled";

    /**
     * 料箱到人业务开关
     */
    public static final String v_W2p = "W2p.enabled";

    //货架搬运，只要盘点，直接上架，人工移库，之中的某个启用，那么货架搬运自驱就启动
    public static final String v_BUCKET_MOVE = "BUCKET_MOVE.enabled";

    public static final String value = "true";

    public static boolean isClassicG2pEnable(List<String> capacities) {
        return capacities.contains(getPrefix(G2P_PICKING))
                || capacities.contains(getPrefix(G2P_GUIDED_PUTAWAY))
                || capacities.contains(getPrefix(G2P_DIRECT_PUTAWAY))
                || capacities.contains(getPrefix(G2P_COUNTCHECK));
    }

    public static boolean isG2PEnable(List<String> capacities) {
        return capacities.contains(getPrefix(G2P_PICKING))
                || capacities.contains(getPrefix(G2P_GUIDED_PUTAWAY))
                || capacities.contains(getPrefix(G2P_DIRECT_PUTAWAY))
                || capacities.contains(getPrefix(G2P_COUNTCHECK))
                || capacities.contains(getPrefix(SI_CARRY))
                || capacities.contains(getPrefix(TALLY))
                || capacities.contains(getPrefix(BUCKET_CONVEY));
    }

    public static boolean isW2PEnable(List<String> capacities) {
        return capacities.contains(getPrefix(W2P_PICKING))
                || capacities.contains(getPrefix(W2P_DIRECT_PUTAWAY))
                || capacities.contains(getPrefix(W2P_GUIDED_PUTAWAY))
                || capacities.contains(getPrefix(W2P_COUNTCHECK));
    }

    private static String getPrefix(String s) {
        return s.substring(0, s.indexOf('.'));
    }

    public static boolean isBucketMoveEnable(List<String> capacities) {
        return capacities.contains(getPrefix(G2P_DIRECT_PUTAWAY))
                || capacities.contains(getPrefix(G2P_COUNTCHECK))
                || capacities.contains(getPrefix(BUCKET_CONVEY));
    }

    public static  boolean isHdsEnable(List<String> capacities) {
        return capacities.contains(getPrefix(SI_HDS)) ||
                capacities.contains(getPrefix(SI_BULL_HDS));
    }
}
