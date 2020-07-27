package com.xck.demo.shiro.realm;

import com.xck.demo.model.user_info;
import com.xck.demo.service.Info_mainService.Info_mainServiceImpl.StudentGetPasswordImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description Shiro权限匹配和账号密码匹配
 * @Author Sans
 */
public class DBRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(DBRealm.class);

    @Autowired
    private StudentGetPasswordImpl studentGetPasswordImpl;


    /**
     *
     *    多重写一个support
     *   标识这个Realm是专门用来验证UsernamePasswordToken
     *   负责验证(UsernamePasswordToken)
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        //这个token就是从过滤器中传入的jwtToken
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 授权权限
     * @Author Sans
     * @CreateTime 2019/6/12 11:44
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("DBRealm AuthorizationInfo");

        return null;
    }

    /**
     * * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {


        // 1. 获取账号密码
        int id = Integer.parseInt(authenticationToken.getPrincipal().toString());
        String password = new String((char[]) authenticationToken.getCredentials());

        logger.info("账号{}",id);
        logger.info("密码{}",password);

        // 2. 求加密后的密码
//        String newPassword = new SimpleHash("MD5", password, id, 2).toString();
        String newPassword = "0cd44355fd830ba2150f68d77f2d2269";

        logger.info("新密码{}",newPassword);

        // 3. 数据库查询
//        User user = (User) userService.getUserByUserNameAndPassword(userName,newPassword).getData();
        user_info user = studentGetPasswordImpl.getUserInfoByIdAndPassword(id,newPassword);


        // 4. 比对
        if (user != null){
            logger.info("获取到该账号");
            //Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getId(),user.getPassword(),ByteSource.Util.bytes(user.getId()),getName());
            return authenticationInfo;
        }
        return null;
    }
}
