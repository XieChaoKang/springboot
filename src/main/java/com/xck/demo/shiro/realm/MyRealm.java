package com.xck.demo.shiro.realm;

import com.xck.demo.model.user_info;
import com.xck.demo.service.Info_mainService.Info_mainServiceImpl.StudentGetPasswordImpl;
import com.xck.demo.service.Shiro_Service.Shiro_ServiceImpl.GetPermissionServiceImpl;
import com.xck.demo.service.Shiro_Service.Shiro_ServiceImpl.GetRolesServiceImpl;
import com.xck.demo.util.RedisUtil;
import com.xck.demo.constant.RedisConstant;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author 谢朝康
 * @date 2020/4/21 0:11
 */
//@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private StudentGetPasswordImpl stuGetPassword;

    @Autowired
    private GetRolesServiceImpl rolesService;

    @Autowired
    private GetPermissionServiceImpl permissionService;

    /**
     * 只有当需要检测用户角色或权限的时候才会调用此方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) throws NullPointerException {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String id = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = (Set<String>) RedisUtil.get( RedisConstant.SHIRO_ROLES_PREFIX+ id);
        Set<String> permissions = (Set<String>) RedisUtil.get(RedisConstant.SHIRO_PERMISSIONS_PREFIX+id);
        if (roles != null && permissions!= null) {
            LoggerFactory.getLogger(JwtRealm.class).info("从缓存里拿到角色和权限信息");
            info.setRoles(roles);
            info.setStringPermissions(permissions);
            return info;
        }
        else {
            roles = rolesService.sel_roles(Integer.parseInt(id));
            permissionService.listpermissionById(Integer.parseInt(id));
            info.setRoles(roles);
            info.setStringPermissions(permissions);

            RedisUtil.set(RedisConstant.SHIRO_ROLES_PREFIX+id,roles,RedisConstant.SHIRO_EXPIRE);
            RedisUtil.set(RedisConstant.SHIRO_PERMISSIONS_PREFIX+id,permissions,RedisConstant.SHIRO_EXPIRE);

            LoggerFactory.getLogger(JwtRealm.class).info("roles:{}"+roles);

            return info;
        }
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getPrincipal().toString();
        System.out.println("test: "+username+"\n");

        user_info user_info = stuGetPassword.getPassword(Integer.parseInt(username));
        if (user_info == null) throw new UnknownAccountException();
        String pwdInDB = user_info.getPassword();
        String salt = user_info.getSalt();
        return new SimpleAuthenticationInfo(username,pwdInDB,ByteSource.Util.bytes(salt),getName());
    }
}
