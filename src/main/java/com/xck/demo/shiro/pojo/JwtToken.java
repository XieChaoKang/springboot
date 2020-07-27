package com.xck.demo.shiro.pojo;


import org.apache.shiro.authc.AuthenticationToken;

/**
 * @program: blog
 * @description: 这个就类似UsernamePasswordToken
 * @author: Lin
 * @create: 2020-04-03 12:28
 **/
public class JwtToken implements AuthenticationToken {

    private String jwt;

    public JwtToken(String jwt) {
        this.jwt = jwt;
    }

    /**
     * 类似是用户名
     * @return
     */
    @Override
    public Object getPrincipal() {
        return jwt;
    }

    /**
     * 类似密码
     * @return
     */
    @Override
    public Object getCredentials() {
        return jwt;
    }
    //返回的都是jwt
}