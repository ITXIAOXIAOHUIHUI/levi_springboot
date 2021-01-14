package com.levi.springboot.controller;

import com.levi.springboot.dto.domain.User;
import com.levi.springboot.mapper.UserMapper;
import com.levi.springboot.model.Request;
import com.levi.springboot.model.Response;
import com.levi.springboot.utils.SoapClientUtils;
import com.levi.springboot.service.impl.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jianghaihui
 * @date 2020/10/30 15:19
 */
@RestController
@RequestMapping(value =  "/device/interface")
@Api("测试操作")
public class DeviceController {

    //@Resource
   // private TestService testService;

    @Autowired(required = false)
    private TestService nextStopRunnable;

    // 继承了BaseMapper，所有的方法都来自己父类
    // 我们也可以编写自己的扩展方法！
    @Resource
    private UserMapper userMapper;

    private static final String KC_WSDL = "http://localhost:10080/soap/user?wsdl";
    @PostMapping(value = "/cxInfo")
    public Response getGKCXInfo(@RequestBody Request request){
          Response response = new Response();
        return  null;

    }


    public Object test2(){
        nextStopRunnable.getStation();
        return  "success";

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


    @PostMapping(value = "/sendCbsStatus")
    public String sendCbsStatus(@RequestBody String requestBody){
        SoapClientUtils sc = new SoapClientUtils();
        String sendCbsStatus = sc.exchange(KC_WSDL, "SendCbsStatus", requestBody);

        System.out.println(sendCbsStatus);
        Response response = new Response();



        return  sendCbsStatus;

    }

    public static void main(String[] args) {
        System.out.println("1".equals("1"));
    }

}

