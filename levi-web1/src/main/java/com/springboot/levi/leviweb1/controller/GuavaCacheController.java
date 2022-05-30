package com.springboot.levi.leviweb1.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.LoadingCache;
import com.springboot.levi.leviweb1.cache.service.RoadWayService;
import com.springboot.levi.leviweb1.cache.service.impl.CacheServiceImpl;
import com.springboot.levi.leviweb1.dto.domain.User;
import com.springboot.levi.leviweb1.mapper.UserMapper;
import com.springboot.levi.leviweb1.model.Response;
import com.springboot.levi.leviweb1.service.impl.TestService;
import com.springboot.levi.leviweb1.utils.SoapClientUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author jianghaihui
 * @date 2020/10/30 15:19
 */
@RestController
@RequestMapping(value = "/device/interface")
@Api(tags = "测试操作")
@Slf4j
public class GuavaCacheController {

    //@Resource
    // private TestService testService;

    @Autowired(required = false)
    private TestService nextStopRunnable;

    @Resource
    private CacheServiceImpl<String,Object> cacheService;

    @Resource
    private RoadWayService roadWayService;
    // 继承了BaseMapper，所有的方法都来自己父类
    // 我们也可以编写自己的扩展方法！
    @Resource
    private UserMapper userMapper;

    private static final String KC_WSDL = "http://localhost:10080/soap/user?wsdl";

    @GetMapping(value = "/test")
    @ApiOperation(value = "测试测试")
    public Response getGKCXInfo(@RequestParam String agvId) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<String, String>();
        map.put("agvId", agvId);
        String res = restTemplate.getForObject("http://172.31.236.102:9001/api/rcs/warehouse/1/task/currentJob?agvId={agvId}", String.class, map);
        System.out.println(res);
        Test result = JSONObject.parseObject(res, Test.class);

        return null;

    }


    @ApiOperation("数据库插入的操作")
    @GetMapping(value = "/test/cache")
    public void testCache() throws InterruptedException, ExecutionException {

        for (long i = 0; i < 10; i++) {
            Object o = cacheService.fetchData("001" + i);
            log.info("-----------------------");
            log.info("get cache:[{}]",o.toString());
        }

        for (long i = 0; i < 10; i++) {
            //roadWayService.initRoadWayCache(i,"kckq");
            LoadingCache cache = cacheService.getCache();
            Thread.sleep(1000);
            Object o = cache.get("001" + i);
            log.info("add cache:[{}]",o.toString());
        }
        for (long i = 0; i < 10; i++) {
            //roadWayService.initRoadWayCache(i,"kckq");
            log.info("=============================");
            LoadingCache cache = cacheService.getCache();
            Object o = cache.get("001" + i);
            log.info("-----------------------");
            log.info("get cache:[{}]",o.toString());
        }
    }

    @ApiOperation("数据库插入的操作")
    @GetMapping(value = "/user/insert")
    public void testInsert() {
        User user = new User();
        user.setName("kwhua_mybatis-plus_insertTest");
        user.setAge(15);
        user.setEmail("310697723@qq.com");
        int result = userMapper.insert(user); // 帮我们自动生成id
        System.out.println(result); // 受影响的行数
        System.out.println(user); // 看到id会自动填充。    }
    }


    @RequestMapping(value = "/sendCbsStatus")
    public String sendCbsStatus(@RequestBody String requestBody) {
        SoapClientUtils sc = new SoapClientUtils();
        String sendCbsStatus = sc.exchange(KC_WSDL, "SendCbsStatus", requestBody);
        System.out.println(sendCbsStatus);
        Response response = new Response();
        return sendCbsStatus;

    }

    public static void main(String[] args) {
        System.out.println("1".equals("1"));
    }

}

