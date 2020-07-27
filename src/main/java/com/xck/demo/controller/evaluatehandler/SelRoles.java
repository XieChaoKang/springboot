package com.xck.demo.controller.evaluatehandler;

import com.xck.demo.shiro.util.JwtUtil;
import com.xck.demo.util.RedisUtil;
import com.xck.demo.util.Result;
import com.xck.demo.constant.RedisConstant;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author 谢朝康
 * @date 2020/4/23 16:34
 * 获取用户角色返回前端，前端根据角色判断能否访问页面
 */
@RestController
public class SelRoles {

    @RequestMapping("/getRoles")
    public Result roles(HttpServletRequest servletRequest) throws NullPointerException{
        LoggerFactory.getLogger(SelRoles.class).info("权限方法到达");
        String token = servletRequest.getHeader("AccessToken");

        String id = JwtUtil.getId(token);
        String key = RedisConstant.SHIRO_ROLES_PREFIX+id;
        Set<String> roles =(Set<String>) RedisUtil.get(key);
        LoggerFactory.getLogger(SelRoles.class).info("roles:{}"+roles);
        assert roles != null;
        String studentRole = "super_stu";
        String teacherRole = "super_tea";
        if (roles.contains(studentRole) || roles.contains(teacherRole)){
            return new Result(232,"用户权限满足！");
        }
        else {
            return new Result(121,"权限不足！！！");
        }
    }
}
