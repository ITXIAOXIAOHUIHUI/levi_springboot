package com.springboot.levi.leviweb1.schuder;

import com.springboot.levi.leviweb1.dto.domain.JobBucketOutDo;

import java.util.List;

/**
 * @author jianghaihui
 * @date 2020/11/10 16:40
 */

public interface JobBucketOutService {

    public List<JobBucketOutDo> selectOneByJobId(String jobId);

    public List<JobBucketOutDo> selectByStats(List<String> doneStates);
    public List<JobBucketOutDo> selectByStatsCancel(List<String> doneStates);

    public boolean jobIsFinish(String line, String led, int jobIsFinish);

    public int updateJobIsFinish(JobBucketOutDo jobBucketOutDo, String jobId);

}
