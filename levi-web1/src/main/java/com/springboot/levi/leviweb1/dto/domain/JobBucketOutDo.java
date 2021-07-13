package com.springboot.levi.leviweb1.dto.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author Mingxiaohui
 * @since 2020/11/7
 **/
@TableName("job_bucket_out")
@Data
public class JobBucketOutDo extends Model<JobBucketOutDo> {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String jobId;

    private Boolean needOutOp;

    private String lineId;

    private String LedNo;

    private int jobIsFinish;

}
