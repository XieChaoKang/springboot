package com.xck.demo.shiro.realm;

import com.xck.demo.model.user_info;
import com.xck.demo.service.Info_mainService.Info_mainServiceImpl.StudentGetPasswordImpl;
import com.xck.demo.service.Shiro_Service.Shiro_ServiceImpl.GetPermissionServiceImpl;
import com.xck.demo.service.Shiro_Service.Shiro_ServiceImpl.GetRolesServiceImpl;
import com.xck.demo.shiro.pojo.JwtToken;
import com.xck.demo.shiro.util.JwtUtil;
import com.xck.demo.util.RedisUtil;
import com.xck.demo.constant.RedisConstant;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author xck
 * @date 2020/5/27 17:13
 */
public class JwtRealm extends AuthorizingRealm {

    //@SuppressWarnings("unchecked")
    //private Stu_getPasswordImpl stuGetPassword = SpringUtils.getBean("");
    @Autowired
    private StudentGetPasswordImpl studentGetPasswordImpl;

    @Autowired
    private GetRolesServiceImpl rolesService;

    @Autowired
    private GetPermissionServiceImpl permissionService;


    /**
     * 多重写一个support
     * 标识这个Realm是专门用来验证JwtToken
     * 不负责验证其他的token（UsernamePasswordToken）
     * @param token
     * @
     *
     *
     * 这里需要重写方法
     *
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        //这个token就是从过滤器中传入的jwtToken
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 1. 获取jwt
        String jwt = principalCollection.getPrimaryPrincipal().toString();

        // 2.判断jwt是否过期
        checkJwt(jwt);

        Set<String> roles = (Set<String>) RedisUtil.get( RedisConstant.SHIRO_ROLES_PREFIX+JwtUtil.getId(jwt));
        Set<String> permissions = (Set<String>) RedisUtil.get(RedisConstant.SHIRO_PERMISSIONS_PREFIX+JwtUtil.getId(jwt));

        if (roles != null && permissions!=null){
            // 3.3. 添加角色与权限
            info.addRoles(roles);
            info.addStringPermissions(permissions);
            LoggerFactory.getLogger(JwtRealm.class).info("从缓存中获取成功,无需重新查询");
            return info;
        }

        else {
            String id = JwtUtil.getId(jwt);
            Set<String> roles1 = rolesService.sel_roles(Integer.parseInt(id));
            Set<String> permissions1 = permissionService.listpermissionById(Integer.parseInt(id));
            info.setRoles(roles1);
            info.setStringPermissions(permissions1);

            RedisUtil.set(RedisConstant.SHIRO_ROLES_PREFIX+id,roles1,RedisConstant.SHIRO_EXPIRE);
            RedisUtil.set(RedisConstant.SHIRO_PERMISSIONS_PREFIX+id,permissions1,RedisConstant.SHIRO_EXPIRE);

            LoggerFactory.getLogger(JwtRealm.class).info("roles:{}"+roles1);

            return info;
        }

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String jwt = (String) authenticationToken.getPrincipal();
        Class<?> aClass = JwtRealm.class;
        Logger logger = LoggerFactory.getLogger(aClass);
        logger.info("jwt:{}"+jwt);

        if (JwtUtil.getId(jwt) == null) {
            logger.error("token无效(空''或者null都不行!)");
            throw new AuthenticationException("token无效");
        }

        logger.info("判断");
        // 1.判断jwt是否合法正确
        checkJwt(jwt);

        user_info user_info = studentGetPasswordImpl.getPassword(Integer.parseInt(JwtUtil.getId(jwt)));
        if (user_info == null) {
            logger.error("用户不存在!)");
            throw new AuthenticationException("用户不存在!");
        }

        logger.info("返回");
        return new SimpleAuthenticationInfo(jwt,jwt,getName());

        // 2. 判断是否在redis中存在
        /*if ( RedisUtil.get(JwtUtil.getId(jwt) + RedisConstant.TOKEN_PREFIX) == null)  {
            String username = JwtUtil.getId(jwt);
            String pass = JwtUtil.getPass(jwt);
            System.out.println("id:"+username+" pass:"+pass);

            //System.out.println(user_info.getPassword());
            if (user_info == null) {
                System.out.println("异常抛出！！");
                throw new AuthenticationException();
            }
            else {
                String pwdInDB = user_info.getPassword();
                String salt = user_info.getSalt();
                System.out.println( salt+":"+ ByteSource.Util.bytes(username)+" 密码:"+pwdInDB);
                return new SimpleAuthenticationInfo(username, pwdInDB, ByteSource.Util.bytes(username), getName());
            }
        }
        else {
            String redisToken =  RedisUtil.get(JwtUtil.getId(jwt) + RedisConstant.TOKEN_PREFIX).toString();
            LoggerFactory.getLogger(aClass).info("token", redisToken);
            if (jwt.equals(redisToken)) {
                return new SimpleAuthenticationInfo(jwt, jwt, "JwtRealm");
            }
            else return null;
        }*/

        //return null;
    }

    /**
     * 检查jwt是否合法
     * @param jwt 密钥
     */
    private void checkJwt(String jwt) {
        LoggerFactory.getLogger(JwtRealm.class).info("判断token");

        if (jwt == null) {
            throw new NullPointerException("jwtToken 不允许为空");
        }

        if (!JwtUtil.verity(jwt,JwtUtil.getPass(jwt))) {
            LoggerFactory.getLogger(JwtRealm.class).info("token不合法！！"+JwtUtil.getPass(jwt));
            throw new AuthenticationException("用户名或密码不正确（token无效，与用户密码不匹配）");
        }
    }
}
