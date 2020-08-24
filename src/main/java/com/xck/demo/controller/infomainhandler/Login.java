package com.xck.demo.controller.infomainhandler;

import com.xck.demo.model.user_info;
import com.xck.demo.service.Info_mainService.Info_mainServiceImpl.Stu_LoginServiceImpl;
import com.xck.demo.service.Shiro_Service.Shiro_ServiceImpl.GetRolesServiceImpl;
import com.xck.demo.shiro.util.JwtUtil;
import com.xck.demo.util.IpUtil;
import com.xck.demo.util.RedisUtil;
import com.xck.demo.util.Result;
import com.xck.demo.constant.RedisConstant;
import io.lettuce.core.RedisURI;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 谢朝康
 * @date 2020/4/4 15:00
 * 登录接口
 */
@Api(tags = "用户登录接口")
@ApiResponses({
        @ApiResponse(code = 0, message = "", response = Result.class)
})
@RestController
public class Login {

    Logger logger = LoggerFactory.getLogger(Login.class);

    @Autowired
    Stu_LoginServiceImpl service;

    @Autowired
    GetRolesServiceImpl getRolesService;

    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id",paramType = "query",readOnly = true),
            @ApiImplicitParam(value = "password",paramType = "query",readOnly = true)
    })
    @RequestMapping("/test_login")
    public Result login(@RequestParam ("id") int id, @RequestParam ("password") String password, HttpServletRequest request) {
        String ipAddress = IpUtil.getIpAddress(request);
        String key = id + RedisConstant.IP_PREFIX + ipAddress;
        Object count = RedisUtil.get(key);
        if (count == null || (int) count == 0 || (int) count < 4) {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                subject.logout();
            }

            UsernamePasswordToken token = new UsernamePasswordToken(String.valueOf(id), password);

            try {

                subject.login(token);

                user_info user = service.Stu_Login(id);
                Map<String, Object> map = new HashMap<>(16);
                map.put("id", user.getId());
                map.put("name", user.getName());
                map.put("pass", user.getPassword());
                map.put("class", user.getAdministrative_class());
                logger.info("方法到达！！:" + user.getId());

                //生成token
                String accessToken = JwtUtil.sign(password, map);
                System.out.println("token:::" + accessToken);
                RedisUtil.set(RedisConstant.TOKEN_PREFIX + id, accessToken, RedisConstant.EXPIRE_TIME);
                map.put("accessToken", accessToken);
                map.remove("id");
                map.remove("class");

                if (subject.hasRole("student") || subject.hasRole("super_stu")) {
                    return Result.success(222, "以学生身份登录成功！", map);
                } else {
                    return Result.success(333, "以老师身份登录！", map);
                }
            } catch (AuthenticationException a) {
                logger.info("登录异常：" + a.getMessage());
                if (RedisUtil.isEmpty(key)){
                    RedisUtil.incr(key);
                }
                else {
                    RedisUtil.set(key, 1, 10, TimeUnit.MINUTES);
                }
                return new Result(111, "账号或密码错误！" + "\n" + "注意：同一IP，密码错误三次将封锁账号!");
            }
         }
        else {
            return new Result(111, "账号已封锁！");
        }

    }

}
