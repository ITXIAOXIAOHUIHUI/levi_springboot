package com.springboot.levi.leviweb1.schuder;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mysql.cj.xdevapi.DatabaseObject;
import com.springboot.levi.leviweb1.dto.ReqBody;
import com.springboot.levi.leviweb1.dto.SIOperationDto;
import com.springboot.levi.leviweb1.dto.SiCancelJobDto;
import com.springboot.levi.leviweb1.dto.domain.JobBucketOutDo;
import com.springboot.levi.leviweb1.dto.domain.WhStatus;
import com.springboot.levi.leviweb1.enums.WcsLock;
import com.springboot.levi.leviweb1.http.utils.ObjectMappers;
import com.springboot.levi.leviweb1.lock.impl.local.SingletonLock;
import com.springboot.levi.leviweb1.mapper.JobBucketOutMapper;
import com.springboot.levi.leviweb1.service.WHstatusService;
import com.springboot.levi.leviweb1.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

/**
 * @Description 消息重发service
 * @Created CaoGang
 * @Date 2020/12/3 14:28
 * @Version 1.0
 */
@Component
@Slf4j
public class ResendSocketMessageBootstrap {
    @Resource
    private JobBucketOutService jobBucketOutService;
    @Resource
    private WHstatusService wHstatusService;

    @Resource
    private JobBucketOutMapper mapper;

    public final  int count = 1000;

    @Scheduled(cron ="*/1 * * * * ?")
    @Async
    public void schedule() {
        List<JobBucketOutDo> jobBucketOutDo = jobBucketOutService.selectOneByJobId("ENTER_STATION");
        System.out.println("22222222222222222");
        jobBucketOutDo.forEach(j -> {
            String result = null;
            try {
                SIOperationDto siOperationDto = new SIOperationDto();
                siOperationDto.setRobotJobId(j.getRobotJobId());
                siOperationDto.setBucketCode(j.getBucketCode());
                siOperationDto.setWarehouseId(1L);
                siOperationDto.setNullFlag(true);
                String postContent = ObjectMappers.mustWriteValue(siOperationDto);
                result = HttpUtils.sendHttpPost("http://172.31.236.33:8071/api/wcs/standardized/operation/notice", postContent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // @Scheduled(cron ="10 * * * * ?")
    /*public void cancelSchedule() {
        List<JobBucketOutDo> notDoneStats = jobBucketOutService.selectByStatsCancel(Lists.newArrayList("DONE", "CANCEL"));
        if(CollectionUtils.isNotEmpty(notDoneStats) && notDoneStats.size() >= count){
            List<JobBucketOutDo> jobBucketOutDos = selectRandomNumbers(notDoneStats, count);
            cancelRobotJob(jobBucketOutDos);
        }else if(CollectionUtils.isNotEmpty(notDoneStats) && notDoneStats.size() < count){
            cancelRobotJob(notDoneStats);
        }
    }*/
      /*  //异步取消
        List<JobBucketOutDo> notDoneStats = jobBucketOutService.selectByStats(Lists.newArrayList("DONE", "CANCEL"));
        if(CollectionUtils.isNotEmpty(notDoneStats) && notDoneStats.size() >= count){
            List<JobBucketOutDo> jobBucketOutDos = selectRandomNumbers(notDoneStats, count);
            cancelRobotJob(jobBucketOutDos);
        }else if(CollectionUtils.isNotEmpty(notDoneStats) && notDoneStats.size() < count){
            cancelRobotJob(notDoneStats);
        }*/


        @Scheduled(cron ="*/60 * * * * ?")
        public void updateStateS() {
            for(int i = 10; i <= 100;i++){
                SingletonLock lock = SingletonLock.valueOf("test:" + i, WcsLock.AGV.getLockType());
                lock.wLock();
                try{
                    Thread.sleep(1000);
                }catch (Exception e){

                }finally {
                    lock.wUnLock();
                }
            }
        }

    public static List<JobBucketOutDo> selectRandomNumbers(List<JobBucketOutDo> numbers, int count) {
        List<JobBucketOutDo> selectedNumbers = new ArrayList<>();
        Random random = new Random();

        int remainingCount = count;
        for (int i = 0; i < numbers.size(); i++) {
            if (random.nextInt(numbers.size() - i) < remainingCount) {
                selectedNumbers.add(numbers.get(i));
                remainingCount--;
                if (remainingCount == 0) {
                    break;
                }
            }
        }

        return selectedNumbers;
    }


    public void cancelRobotJob(List<JobBucketOutDo> numbers){
        numbers.forEach( n->{
            SiCancelJobDto siOperationDto = new SiCancelJobDto();
            siOperationDto.setRobotJobId(n.getRobotJobId());
            siOperationDto.setWarehouseId(1L);
            siOperationDto.setReason("1111");
            String postContent = ObjectMappers.mustWriteValue(siOperationDto);
            try {
                HttpUtils.sendHttpPost("http://172.31.236.33:8071/api/wcs/standardized/robot/job/cancel", postContent);
            } catch (Exception e) {

            }
        });

    }


    public static void main(String[] args) {
            //90
        Double d1 = 1.5707963267948966;
        // 0
        Double d2 = 0.0;
        //180
        Double d3 = 3.141592653589793;
        //-90
        Double d4 = 4.71238898038469;
        BigDecimal two = BigDecimal.valueOf(d4);
        double result = two.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        int angle = (int)((result * 180)/3.14);
        if(angle == 270){
            angle = -90;
        }
        System.out.println(String.valueOf(angle));





        AtomicBoolean  atomicBoolean = new AtomicBoolean(false);
        System.out.println(atomicBoolean.get());
        atomicBoolean.set(true);
        System.out.println(atomicBoolean.get());
       /* Map<String,String> map  = Maps.newHashMap();
        map.put("1","111");
        map.put("2","222");
        map.put("3","333");
        map.put("4","4444");
        for(Map.Entry<String, String> entry   : map.entrySet()){
            for(int i = 0 ;i <= 2 ;i++){
                if(i == 1 && entry.getKey().equals("2")){
                    System.out.println("aaaaaaaaaa"+i);
                    break;
                }
                System.out.println("dddddd"+i);
            }
            System.out.println(entry.getValue());
        }*/

        System.out.println(test(true));
    }

    public static int test(boolean flag){
            try{
                if(flag){
                    System.out.println("11111");
                    return 1;
                }
            }finally {
                System.out.println("2222");
                return 2;
            }
    }
   public static String getResult(String test){
        if(test == null){
            return null;

        }


       System.out.println("1111");
        return "11";
   }


}
