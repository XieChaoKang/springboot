package com.xck.demo.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.xck.demo.shiro.pojo.JwtToken;
import com.xck.demo.shiro.util.JwtUtil;
import com.xck.demo.util.RedisUtil;
import com.xck.demo.util.Result;
import com.xck.demo.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @description: 自定义一个Filter，用来拦截所有的请求判断是否携带Token
 *               isAccessAllowed()判断是否携带了有效的JwtToken
 *               onAccessDenied()是没有携带JwtToken的时候进行账号密码登录，登录成功允许访问，登录失败拒绝访问
 * @author: 谢朝康
 * @date: 2020-04-24 12:28
 **/
@Slf4j
public class JwtFilter extends AccessControlFilter {

    /**
     *   返回true，shiro就直接允许访问url
     *   返回false，shiro才会根据onAccessDenied的方法的返回值决定是否允许访问url
     * @param servletRequest
     * @param servletResponse
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {
        // 如果请求头中不包含Authorization 则放行 否则拦截
        String accessToken = getAccessToken((HttpServletRequest) servletRequest);
        // Header 含有 accessToken 拦截 跳转到 onAccessDenied
        return accessToken == null? true:false;

    }

    /**
     * 返回结果为true表明登录通过
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        String accessToken = getAccessToken((HttpServletRequest) servletRequest);

        // 每次请求到需要验证的方法  就会走这里
        JwtToken jwtToken = new JwtToken(accessToken);
       // LoggerFactory.getLogger(JwtFilter.class).info("拦截方法:"+accessToken);
        /*
         * 下面就是固定写法
         * */
        try {
           // LoggerFactory.getLogger(JwtFilter.class).info("拦截方法2");
            // 检查redis是否还存在缓存
            if (!checkAccessTokenInRedis(JwtUtil.getId(accessToken))){
                throw new Exception("token过期");
            }

           // LoggerFactory.getLogger(JwtFilter.class).info("拦截方法3");

            // 刷新jwtToken
            if (JwtUtil.verity(accessToken,JwtUtil.getPass(accessToken))) {
                RedisUtil.expire(RedisConstant.TOKEN_PREFIX + JwtUtil.getId(accessToken), RedisConstant.EXPIRE_TIME);
            }
            else {
                onLoginFail(servletResponse);
            }

        } catch (Exception e) {
            // 用下面的方法向客户端返回错误信息
            onLoginFail(servletResponse);
            return false;
        }



        return true;
        //执行方法中没有抛出异常就表示登录成功
    }

    /**
     * 登录失败时默认返回 401 状态码
     * @param response
     * @throws IOException
     */
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        String errorResult = JSON.toJSONString(new Result(401,"失效！！请重新登录！！"));
        Writer out= new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        out.write(errorResult);
        out.flush();
        out.close();
    }

    /**
     * 检查token是否有效
     * @param id
     * @return
     */
    private boolean checkAccessTokenInRedis(String id){
       // LoggerFactory.getLogger(JwtFilter.class).info("id:"+(RedisConstant.TOKEN_PREFIX+id)+"查询失败："+RedisConstant.TOKEN_PREFIX+id);
        if (RedisUtil.get( RedisConstant.TOKEN_PREFIX+id) != null){
          //  LoggerFactory.getLogger(JwtFilter.class).info("查询失败：2");
            return true;
        }
        else {
           // LoggerFactory.getLogger(JwtFilter.class).info("查询失败：3");
            return false;
        }
    }

    /**
     * 获取AccessToken
     * @param servletRequest
     * @return
     */
    private String getAccessToken(HttpServletRequest servletRequest) {
        HttpServletRequest request = servletRequest;
        return request.getHeader("AccessToken");
    }

}
