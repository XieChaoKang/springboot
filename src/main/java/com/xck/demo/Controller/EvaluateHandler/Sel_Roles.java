package com.xck.demo.Controller.EvaluateHandler;

import com.xck.demo.Shiro.util.JwtUtil;
import com.xck.demo.Util.RedisUtil;
import com.xck.demo.Util.Result;
import com.xck.demo.constant.RedisConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
public class Sel_Roles {

    @RequestMapping("/getRoles")
    public Result roles(HttpServletRequest servletRequest) throws NullPointerException{
        LoggerFactory.getLogger(Sel_Roles.class).info("权限方法到达");
        String token = servletRequest.getHeader("AccessToken");
        //System.out.println("获取到token:"+token+"  id:"+ JwtUtil.getId(token));
        String id = JwtUtil.getId(token);
        String key = RedisConstant.SHIRO_ROLES_PREFIX+id;
        Set<String> roles =(Set<String>) RedisUtil.get(key);
        assert roles != null;
        if (roles.contains("super_stu") || roles.contains("super_tea")){
            return new Result(232,"用户权限满足！");
        }
        else {
            return new Result(121,"权限不足！！！");
        }
    }
}
