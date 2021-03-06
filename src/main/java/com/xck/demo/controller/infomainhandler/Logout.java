package com.xck.demo.controller.infomainhandler;

import com.xck.demo.util.Result;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 谢朝康
 * @date 2020/4/25 22:56
 * 用户登出系统，清除用户登录信息记录
 */
@Api(tags = "退出接口，清除用户登录信息记录")
@RestController
public class Logout {

    @RequestMapping("/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
            return new Result(203,"成功登出");
        }catch (Exception e){
            return new Result(103,"登出失败!!"+e.getMessage());
        }
    }
}
