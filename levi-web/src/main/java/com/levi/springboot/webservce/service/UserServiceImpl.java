package com.levi.springboot.webservce.service;

import com.levi.springboot.webservce.domain.User;
import com.levi.springboot.webservce.domain.WSRequestRoot;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName:UserServiceImpl
 * @Description:测试服务接口实现类
 */
@Component
public class UserServiceImpl implements UserService {

    private Map<String, User> userMap = new HashMap<String, User>();


    @Override
    public String SendCbsAddress( WSRequestRoot requestImportRoot) {
        System.out.println(requestImportRoot);
        return null;
    }

    @Override
    public String SendCbsStatus(String jsonPara) {

        System.out.println(jsonPara);

        return "SUCCESS";
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
