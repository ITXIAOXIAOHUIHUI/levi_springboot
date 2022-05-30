package com.springboot.levi.leviweb1.schuder;

import com.springboot.levi.leviweb1.dto.ReqBody;
import com.springboot.levi.leviweb1.dto.domain.JobBucketOutDo;
import com.springboot.levi.leviweb1.dto.domain.WhStatus;
import com.springboot.levi.leviweb1.service.WHstatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

    @Scheduled(fixedDelay = 2000L)
    private void schedule() {
       /* JobBucketOutDo jobBucketOutDo = jobBucketOutService.selectOneByJobId("111");
        WhStatus whStatus = new WhStatus();
        whStatus.setLed1("0");
        wHstatusService.updateOne(whStatus,"L007");*/

    }

    public static void main(String[] args) {


        String key = "key";
        convertFunction(s2 -> {
            System.out.println(s2);
            ReqBody reqBody = new ReqBody();
            reqBody.setSourceTaskId("1111");
            System.out.println(reqBody);
            return reqBody;
        },key);

    }

    public static ReqBody convertFunction(Function<String, ReqBody> reqBody,String key){
        return reqBody.apply(key);

    }


    public static void test(WhStatus whStatus, JobBucketOutDo jobBucketOutDo) {

        if (jobBucketOutDo.getLedNo().equals(LedConstant.LED1)) {
            whStatus.setLed1("0");
        }
        if (jobBucketOutDo.getLedNo().equals(LedConstant.LED2)) {
            whStatus.setLed2("0");
        }
        if (jobBucketOutDo.getLedNo().equals(LedConstant.LED3)) {
            whStatus.setLed3("0");
        }
        if (jobBucketOutDo.getLedNo().equals(LedConstant.LED4)) {
            whStatus.setLed4("0");
        }
    }
}
