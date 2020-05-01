package com.xck.demo.Controller.EvaluateHandler;

import com.xck.demo.Util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 谢朝康
 * @date 2020/4/23 16:34
 * 获取用户角色返回前端，前端根据角色判断能否访问页面
 */
@RestController
public class Sel_Roles {

    @RequestMapping("/getRoles")
    public Result roles() throws NullPointerException{
        LoggerFactory.getLogger(Sel_Roles.class).info("权限方法到达");
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("super_stu") || subject.hasRole("super_tea")){
            return new Result(232,"用户权限满足！");
        }
        else {
            return new Result(121,"权限不足！！！");
        }
    }
}
