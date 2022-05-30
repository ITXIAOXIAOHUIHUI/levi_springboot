package com.springboot.levi.leviweb1.schuder;

import com.springboot.levi.leviweb1.dto.domain.JobBucketOutDo;

/**
 * @author jianghaihui
 * @date 2020/11/10 16:40
 */

public interface JobBucketOutService {

    public JobBucketOutDo selectOneByJobId(String jobId);

    public boolean jobIsFinish(String line, String led, int jobIsFinish);

    public int updateJobIsFinish(JobBucketOutDo jobBucketOutDo, String jobId);

}
