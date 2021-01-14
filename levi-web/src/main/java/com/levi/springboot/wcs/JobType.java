package com.levi.springboot.wcs;

import com.google.common.collect.ImmutableSet;

/**
 * @author quzhe
 * @date 2019/10/30 12:56
 * @description 任务类型
 */
public enum JobType {
    G2P_BUCKET_MOVE,
    G2P_PICK,
    G2P_OFFLINE_PICK,
    G2P_ONLINE_PICK,
    G2P_COUNTCHECK,
    G2P_GUIDED_PUTAWAY,
    G2P_DIRECT_PUTAWAY,
    G2P_ONLINE_TALLY,
    TALLY_OFFLINE_MANUAL,
    RESET_JOB,
    SI_CARRY;

    /**
     * 当前任务类型和工作站无关
     */
    public boolean nonStation() {
        return ImmutableSet.of(RESET_JOB).contains(this);
    }
}
