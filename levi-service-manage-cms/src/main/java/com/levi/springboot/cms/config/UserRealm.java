package com.levi.springboot.cms.config;

import com.levi.springboot.cms.utils.ShiroUtils;
import com.levi.springboot.mapper.SysMenuMapper;
import com.levi.springboot.mapper.SysUserMapper;
import com.levi.springboot.model.entity.SysMenuEntity;
import com.levi.springboot.model.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author jianghaihui
 * @date 2019/9/18 10:55
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Resource
    private SysUserMapper userMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * 授权（验证权限时调用）
     * 获取用户权限集合
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUserEntity user = (SysUserEntity) principals.getPrimaryPrincipal();
        if(user == null){
            throw  new UnknownAccountException("账号不存在");
        }
        List<String> permsList;
        List<SysMenuEntity> menuList = sysMenuMapper.selectList();
        permsList = new ArrayList<>(menuList.size());
        menuList.forEach(menu->{
            permsList.add(menu.getPerms());
        });
        //用户权限列表
        Set<String> permSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isEmpty(perms)){
                continue;
            }
            permSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        SimpleAuthorizationInfo info  = new SimpleAuthorizationInfo();
        info.setStringPermissions(permSet);
        return info;
    }

    /**
     * 认证（登录时调用）
     * @param token
     * @return 验证用户登录
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken token1 = (UsernamePasswordToken)token;
        //查询用户信息
        SysUserEntity user = userMapper.selectOne(token1.getUsername());
        if(user == null){
            throw  new UnknownAccountException("账号或密码不正确");
        }
        if(user.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定，请联系管理人员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName());
        return info;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
