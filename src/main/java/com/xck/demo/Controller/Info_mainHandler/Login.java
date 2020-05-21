package com.xck.demo.Controller.Info_mainHandler;

import com.xck.demo.Model.user_info;
import com.xck.demo.Service.Info_mainService.Info_mainServiceImpl.Stu_LoginServiceImpl;
import com.xck.demo.Service.Shiro_Service.Shiro_ServiceImpl.GetRolesServiceImpl;
import com.xck.demo.Util.Result;
import com.xck.demo.VO.InfoVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 谢朝康
 * @date 2020/4/4 15:00
 * 登录接口
 */
@RestController
public class Login {

    Logger logger = LoggerFactory.getLogger(Login.class);

    @Autowired
    Stu_LoginServiceImpl service;

    @Autowired
    GetRolesServiceImpl getRolesService;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @RequestMapping("/test_login")
    public Result login(@RequestParam ("id") int id, @RequestParam ("password") String password){

        HashMap<Object, Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }

        UsernamePasswordToken token = new UsernamePasswordToken(String.valueOf(id),password);

        try {
            logger.info("方法到达！！");
            subject.login(token);

            user_info user_info = service.Stu_Login(id);
            map.put("id",user_info.getId());
            map.put("name",user_info.getName());
            map.put("class",user_info.getAdministrative_class());

            if (subject.hasRole("student") || subject.hasRole("super_stu")) {

                return new Result(222, "以学生身份登录成功！",map);
            }
            else {
                return new Result(333,"以老师身份登录！",map);
            }
        }
        catch (AuthenticationException a){
           // logger.info(a.getMessage());
            return new Result(111,"账号或密码错误！");
        }

    }

}
