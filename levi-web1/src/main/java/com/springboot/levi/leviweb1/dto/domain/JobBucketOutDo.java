package com.springboot.levi.leviweb1.dto.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author Mingxiaohui
 * @since 2020/11/7
 **/
@TableName("bucket_robot_job")
@Data
public class JobBucketOutDo extends Model<JobBucketOutDo> {

    public static final String PREFIX = "SIBucket_";

    private long id;
    private int priority;
    private String robotJobId;

    /**
     * 设备编码，比如电梯编码
     */
    private String deviceCode;
    /**
     * 任务优先级类型 0-普通优先级（默认）， 1-强制优先级
     */
    private Integer priorityType;
    /**
     * 计划完成时间:截至时间之前需要任务执行完成
     */
    private String deadline;


    /**
     * 推送标识,未推送上游-0，已推送-1
     */
    private Integer pushFlag;
    /**
     * 货位编号
     */
    private String targetBucketSlotCode;

    private String state;
    /**
     * 货架编号
     */
    private String bucketCode;

    /**
     * 货架坐标(起始点)
     */
    private String startPoint;

    /**
     * 起点简码
     */
    private String startPointName;

    /**
     * 货架类型
     */
    private String bucketType;

    /**
     * 区域到点任务的 ，起始区域
     */
    private String startArea;

    /**
     * 作业面
     */
    private Integer workFace;
    /**
     * 作业面
     */
    private String workFaces;
    /**
     * 目标区域
     */
    private String endArea;
    /**
     * 目标点
     */
    private String targetPoint;

    /**
     * 目标简码
     */
    private String targetPointName;

    private String agvEndPoint;

    private Integer putDown;

    private Boolean needOperation;

    private String jobType;

    private String upJobType;

    private Boolean standByFlag = Boolean.FALSE;

    //    //对任务添加相关注释
    //针对RCS下发的跨楼层小车移位任务传递信息用
    private String remark;

    private String dispatchState;

    /**
     * 任务组id，业务字段
     */
    private String robotJobGroupId;

    /** 任务组任务数量 */
    private Integer robotJobGroupNum;

    /**
     * 业务组id,用于关联bucket_move_job
     */
    private String busiGroupId;

    /*
     * hds任务组ID
     */
    private String hdsGroupId;

    private Integer sequence;

    /**
     * 货位编号
     */
    private String bucketSlotCode;

    private String cancelStrategy;

    private Boolean lockFlag;

    private Boolean needReset;

    /**
     * 是否校验编码 0-不校验，1-校验（默认）
     */
    private Integer checkCode = 1;

    /**
     * 是否出场 默认不出场
     */
    private Integer needOut = 0;

    /**
     * 货架类型
     */
    private String bucketTypeCode;

    /**
     * 按位与的一个标志字段
     */
    private int flag;

    private String stationCode;

    /**
     * 业务类型，拣选，上架，盘点,非枚举,有可能叫xxx  workbin-default  worbin-line
     */
    private String businessType;

    /**
     * 作业模式，WORKBIN_DEFAULT,WORKBIN_LINE
     */
    private String workMode;
    /**
     * 任务来源
     */
    private String source;
    /**
     * 密集存储点到区域二段位移终点
     */
    private String secondEndPoint;

    /**
     * 空车移动-任务倒计时(单位:秒)
     */
    private Integer taskCountDown;
    /**
     * AGV_MOVE车速
     */
    private String speed;

    /**
     * 货架简码
     */
    @TableField(exist = false)
    private String alias;

}
