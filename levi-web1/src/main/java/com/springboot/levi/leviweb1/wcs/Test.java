package com.springboot.levi.leviweb1.wcs;

import com.google.common.collect.Maps;
import com.springboot.levi.leviweb1.utils.WorkMode;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author jianghaihui
 * @date 2021/1/5 16:05
 */
public class Test {


    /**
     * 以作业模式的运力分配的下限作为 平滑加权轮询算法的固定权重
     * G2P_COUNTCHECK|G2P_PICKING|G2P_DIRECT_PUTAWAY|G2P_GUIDED_PUTAWAY 和权重
     */
    private Map<JobType, Integer[]> parseFixedWeight(List<ConfigDto> configs) {
        //映射的名称不一致， 直接写死吧
        Map<JobType, Integer[]> jobTypeMap = Maps.newEnumMap(JobType.class);
        jobTypeMap.put(JobType.G2P_PICK, new Integer[]{5, 10});
        jobTypeMap.put(JobType.G2P_COUNTCHECK, new Integer[]{1, 10});
        jobTypeMap.put(JobType.G2P_DIRECT_PUTAWAY, new Integer[]{2, 10});
        jobTypeMap.put(JobType.G2P_GUIDED_PUTAWAY, new Integer[]{2, 10});
        jobTypeMap.put(JobType.RESET_JOB, new Integer[]{2, 10});
        jobTypeMap.put(JobType.SI_CARRY, new Integer[]{2, 10});
        jobTypeMap.put(JobType.TALLY_OFFLINE_MANUAL, new Integer[]{1, 3});

        for (ConfigDto config : configs) {
            String[] arr = config.getName().split("\\|");
            if (config.getName() == null || !config.getName().contains("_AGV_CAPACITY") || arr.length != 2 || StringUtils.isBlank(config.getValue()))
                continue;
            JobType type = "G2P_PICKING".equals(arr[0]) ? JobType.G2P_PICK : JobType.valueOf(arr[0]);
            if (arr[1].equals("MIN_STATION_AGV_CAPACITY")) {
                //从配置中获取最小值
                //int minAgvCapacity = MiscUtils.parseIntValue(config.getValue());
                jobTypeMap.get(type)[0] = 2;
            } else if (arr[1].equals("MAX_STATION_AGV_CAPACITY")) {
                //从配置中获取最大值
                // int maxAgvCapacity = MiscUtils.parseIntValue(config.getValue());
                jobTypeMap.get(type)[1] = 8;
            }
        }
        return jobTypeMap;
    }

    public static void main(String[] args) {
        System.out.println(WorkMode.SI_CARRY);
    }

}
