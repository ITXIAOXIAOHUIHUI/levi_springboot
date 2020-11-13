package com.mracale.springboot.Service.impl;

import com.levi.springboot.webservce.domain.User;
import com.levi.springboot.webservce.domain.WSRequestRoot;
import com.levi.springboot.webservce.service.UserService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName:UserServiceImpl
 * @Description:测试服务接口实现类
 */
@WebService(serviceName="UserService",//对外发布的服务名
        targetNamespace="http://service.springboot.mracale.com",//指定你想要的名称空间，通常使用使用包名反转
        endpointInterface="com.levi.springboot.webservce.service.UserService")//服务接口全路径, 指定做SEI（Service EndPoint Interface）服务端点接口
@Component
public class UserServiceImpl implements UserService {

    private Map<String, User> userMap = new HashMap<String, User>();


    @Override
    public String SendCbsAddress( WSRequestRoot requestImportRoot) {
        System.out.println(requestImportRoot);
        return null;
    }

    //@Override
    public String SendCbsStatus(String jsonPara) {
        System.out.println(jsonPara);
        return null;
    }

    @Override
    public User getUser(String userId) {
        System.out.println("向实体类插入数据");
        User user = new User();
        user.setUserId(UUID.randomUUID().toString().replace("-", ""));
        user.setUserName("mracale01");
        user.setEmail("mracale01@163.xom");
        userMap.put(user.getUserId(), user);

        user = new User();
        user.setUserId(UUID.randomUUID().toString().replace("-", ""));
        user.setUserName("mracale02");
        user.setEmail("mracale02@163.xom");
        userMap.put(user.getUserId(), user);

        user = new User();
        user.setUserId(UUID.randomUUID().toString().replace("-", ""));
        user.setUserName("mracale03");
        user.setEmail("mracale03@163.xom");
        userMap.put(user.getUserId(), user);
        return null;
    }
}
