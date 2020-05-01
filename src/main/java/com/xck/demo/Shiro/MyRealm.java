package com.xck.demo.Shiro;

import com.xck.demo.Model.user_info;
import com.xck.demo.Service.Info_mainService.Info_mainServiceImpl.Stu_getPasswordImpl;
import com.xck.demo.Service.Shiro_Service.Shiro_ServiceImpl.GetPermissionServiceImpl;
import com.xck.demo.Service.Shiro_Service.Shiro_ServiceImpl.GetRolesServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author 谢朝康
 * @date 2020/4/21 0:11
 */
//@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private Stu_getPasswordImpl stuGetPassword;

    @Autowired
    private GetRolesServiceImpl rolesService;

    @Autowired
    private GetPermissionServiceImpl permissionService;

    /**
     * 只有当需要检测用户角色或权限的时候才会调用此方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) throws NullPointerException {
        String id = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = rolesService.sel_roles(Integer.parseInt(id));
        Set<String> permissions = permissionService.listpermissionById(Integer.parseInt(id));
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
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
