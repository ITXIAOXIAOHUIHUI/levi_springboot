package com.levi.springboot.cms.controller;

import com.levi.springboot.cms.utils.ShiroUtils;
import com.levi.springboot.mapper.SysMenuMapper;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Shrio 测试方法控制层
 */
@RestController
public class ShiroController {
    private static Logger LOGGER = LoggerFactory.getLogger(ShiroController.class);

    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * 登录测试
     * http://localhost:7011/userLogin?userName=admin&passWord=admin
     */
    @RequestMapping("/userLogin")
    public void userLogin(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "passWord") String passWord) {
        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
            subject.login(token);
            LOGGER.info("登录成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务器每次重启请求该接口之前必须先请求上面登录接口
     * http://localhost:7011/menu/list 获取所有菜单列表
     * 权限要求：sys:user:shiro
     */
    @RequestMapping("/menu/list")
    @RequiresPermissions("sys:user:shiro")
    public List list() {
        return sysMenuMapper.selectList();
    }

    /**
     * 用户没有该权限，无法访问
     * 权限要求：ccc:ddd:bbb
     */
    @RequestMapping("/menu/list2")
    @RequiresPermissions("ccc:ddd:bbb")
    public List list2() {
        return sysMenuMapper.selectList();
    }

    /**
     * 退出测试
     */
    @RequestMapping("/userLogOut")
    public String logout() {
        ShiroUtils.logout();
        return "success";
    }

    public static void main(String[] args) {
        // 初始化
        List<Student> students = new ArrayList<Student>() {
            {
                add(new Student(20160001, "孔明", 20, 1, "土木工程", "武汉大学"));
                add(new Student(20160002, "伯约", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20160003, "玄德", 22, 3, "经济管理", "武汉大学"));
                add(new Student(20160004, "云长", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20161001, "翼德", 21, 2, "机械与自动化", "华中科技大学"));
                add(new Student(20161002, "元直", 23, 4, "土木工程", "华中科技大学"));
                add(new Student(20161003, "奉孝", 23, 4, "计算机科学", "华中科技大学"));
                add(new Student(20162001, "仲谋", 22, 3, "土木工程", "浙江大学"));
                add(new Student(20162002, "鲁肃", 23, 4, "计算机科学", "浙江大学"));
                add(new Student(20163001, "丁奉", 24, 5, "土木工程", "南京大学"));
            }
        };
        for (int limit = 3, skip = 0; skip < students.size(); skip = skip + limit) {
            System.out.println(students.stream().skip(skip).limit(limit).collect(Collectors.toList()));
            System.out.println("=======================");
        }
    }
}