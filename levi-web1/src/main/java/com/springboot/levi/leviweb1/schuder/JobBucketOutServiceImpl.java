package com.springboot.levi.leviweb1.schuder;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.levi.leviweb1.dto.domain.JobBucketOutDo;
import com.springboot.levi.leviweb1.mapper.JobBucketOutMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jianghaihui
 * @date 2020/11/10 16:41updateJobIsFinish
 */
@Service
public class JobBucketOutServiceImpl implements JobBucketOutService {

    @Resource
    private JobBucketOutMapper mapper;


    @Override
    public JobBucketOutDo selectOneByJobId(String jobId) {
        QueryWrapper<JobBucketOutDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("job_id", jobId);
        return mapper.selectOne(queryWrapper);
    }

    /**
     * 判断当前的任务是否做完，没有做完，要等做完才可以再次下发任务
     * 重复按灯
     * 有任务没做完，就不让它再次下发任务
     *
     * @param line
     * @param led
     * @param jobIsFinish
     * @return
     */
    @Override
    public boolean jobIsFinish(String line, String led, int jobIsFinish) {
        QueryWrapper<JobBucketOutDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("line_id", line);
        queryWrapper.eq("led_no", led);
        queryWrapper.eq("job_is_finish", jobIsFinish);
        JobBucketOutDo jobBucketOutDo = mapper.selectOne(queryWrapper);
        if (jobBucketOutDo != null) {
            return true;
        }
        return false;
    }

    @Override
    public int updateJobIsFinish(JobBucketOutDo jobBucketOutDo, String jobId) {
        QueryWrapper<JobBucketOutDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("job_id", jobId);
        int update = mapper.update(jobBucketOutDo, queryWrapper);
        return update;
    }
}
