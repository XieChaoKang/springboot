package com.xck.demo.Controller.Info_mainHandler;

import com.xck.demo.Model.user_info;
import com.xck.demo.Service.Info_mainService.Info_mainServiceImpl.Change_PassServiceImpl;
import com.xck.demo.Service.Info_mainService.Info_mainServiceImpl.Stu_getPasswordImpl;
import com.xck.demo.Util.Result;
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
@Controller
public class Up_Pass {

    @Autowired
    Stu_getPasswordImpl service;

    @Autowired
    Change_PassServiceImpl change_passService;

    @ResponseBody
    @RequestMapping("/up_pass")
    public Result up_pass(@RequestParam("id") int id,@RequestParam ("password") String password, @RequestParam ("new_pass") String new_pass, HttpServletRequest request) throws UnsupportedEncodingException{
        Logger logger = LoggerFactory.getLogger(Up_Pass.class);
        logger.info("修改密码");

        String passInDB = service.getPassword(id).getPassword();
        //String salt = user_info1.getSalt();
        String salt = String.valueOf(ByteSource.Util.bytes(String.valueOf(id)));

        String md5 = new SimpleHash("md5", password,salt,2).toString();

        //判断用户旧密码是否正确
        if (md5.equals(passInDB)){
            logger.info("修改密码到达");
            user_info user_info = new user_info(id,new SimpleHash("md5",new_pass,salt,2).toString());
            //更改密码
            if( change_passService.Up_Pass(user_info) != 0){
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
