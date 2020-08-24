package com.xck.demo.controller.infomainhandler;

import com.xck.demo.model.user_info;
import com.xck.demo.service.Info_mainService.Info_mainServiceImpl.ChangePassServiceImpl;
import com.xck.demo.service.Info_mainService.Info_mainServiceImpl.StudentGetPasswordImpl;
import com.xck.demo.shiro.util.JwtUtil;
import com.xck.demo.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author 谢朝康
 * @date 2020/4/12 1:25
 * 更改密码接口
 */
@Api("更改密码接口")
@Controller
public class UpdatePassword {

    @Autowired
    StudentGetPasswordImpl getPassword;

    @Autowired
    ChangePassServiceImpl passService;

    @ApiOperation("更改密码接口，需要把token放在请求头")
    @ApiImplicitParams({
           @ApiImplicitParam(value = "password",paramType = "query",readOnly = true),
            @ApiImplicitParam(value = "new_pass",paramType = "query",readOnly = true)
    })
    @ResponseBody
    @RequestMapping("/up_pass")
    public Result upPass(HttpServletRequest servletRequest, @RequestParam ("password") String password, @RequestParam ("new_pass") String newPass) throws UnsupportedEncodingException{
        Logger logger = LoggerFactory.getLogger(UpdatePassword.class);
        logger.info("修改密码");
        String token = servletRequest.getHeader("AccessToken");
        String id = JwtUtil.getId(token);

        assert id != null;
        //从数据库获取密码
        String passcode = getPassword.getPassword(Integer.parseInt(id)).getPassword();
        String salt = String.valueOf(ByteSource.Util.bytes(id));

        String md5 = new SimpleHash("md5", password,salt,2).toString();

        //判断用户旧密码是否正确
        if (md5.equals(passcode)){
            logger.info("修改密码到达");
            user_info user_info = new user_info(id,new SimpleHash("md5",newPass,salt,2).toString());
            //更改密码
            if( passService.Up_Pass(user_info) != 0){
                return new Result(222,"修改成功");
            }
            else {
                return new Result(123,"系统错误");
            }
        }
        else {
            return new Result(55,"旧密码输入错误");
        }
    }
}
