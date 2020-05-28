package com.xck.demo.Controller.Info_mainHandler;

import com.xck.demo.Model.user_info;
import com.xck.demo.Service.Info_mainService.Info_mainServiceImpl.Stu_LoginServiceImpl;
import com.xck.demo.Service.Shiro_Service.Shiro_ServiceImpl.GetRolesServiceImpl;
import com.xck.demo.Shiro.pojo.JwtToken;
import com.xck.demo.Shiro.util.JwtUtil;
import com.xck.demo.Util.RedisUtil;
import com.xck.demo.Util.Result;
import com.xck.demo.constant.RedisConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/test_login")
    public Result login(@RequestParam ("id") int id, @RequestParam ("password") String password){

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }

        UsernamePasswordToken token = new UsernamePasswordToken(String.valueOf(id),password);

        try {

            subject.login(token);

            user_info user = service.Stu_Login(id);
            Map<String,Object> map = new HashMap<>(16);
            map.put("id",user.getId());
            map.put("name",user.getName());
            map.put("pass",user.getPassword());
            map.put("class",user.getAdministrative_class());
            logger.info("方法到达！！:"+user.getId());

            //生成token
            String accessToken = JwtUtil.sign(password, map);
            System.out.println("token:::"+accessToken);
            RedisUtil.set( RedisConstant.TOKEN_PREFIX+id,accessToken,RedisConstant.EXPIRE_TIME);
            map.put("accessToken",accessToken);
            map.remove("id");
            map.remove("class");

            //request.getSession().setAttribute("student",new user_info(user_info.getId(), user_info.getPassword(), user_info.getName(), user_info.getSalt()));
            if (subject.hasRole("student") || subject.hasRole("super_stu")) {
                return Result.success(222, "以学生身份登录成功！",map);
            }
            else {
                return Result.success(333,"以老师身份登录！",map);
            }
        }
        catch (AuthenticationException a){
             logger.info("登录异常："+a.getMessage());
            return new Result(111,"账号或密码错误！");
        }

    }

    /*public Result login(@RequestParam ("id") int id, @RequestParam ("password") String password){

        HashMap<String, Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();

//        UsernamePasswordToken token = new UsernamePasswordToken(String.valueOf(id),password);

        Map info = new HashMap();
        info.put("id",String.valueOf(id));
        info.put("pass",String.valueOf(password));
        String  token = JwtUtil.sign(password,info);
        JwtToken jwtToken = new JwtToken(token);

        try {
            logger.info("方法到达！！");
            subject.login(jwtToken);

            user_info user_info = service.Stu_Login(id);
            map.put("id",user_info.getId());
            map.put("name",user_info.getName());
            map.put("class",user_info.getAdministrative_class());
        }
        catch (AuthenticationException a){
            logger.info("异常："+a.getMessage());
            return new Result(111,"账号或密码错误！");
        }
        if (subject.isAuthenticated()){
            String accessToken = JwtUtil.sign(password, map);
            System.out.println("token:::"+accessToken);
            RedisUtil.set( RedisConstant.TOKEN_PREFIX+id,accessToken,RedisConstant.EXPIRE_TIME);
            map.put("accessToken",accessToken);
            map.remove("id");
            map.remove("class");
            if (subject.hasRole("student") || subject.hasRole("super_stu")) {
                return  Result.success(222, "以学生身份登录成功！",map);
            }
            else {
                return  Result.success(333,"以老师身份登录！",map);
            }
        }

        return new Result(111,"登录失败");

    }
*/
}
