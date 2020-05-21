package com.xck.demo.Exception;

import com.xck.demo.Util.Result;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 谢朝康
 * @date 2020/4/25 20:39
 * 全局异常处理
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new Result(1001,"参数校验失败！！"+objectError.getDefaultMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public Result LoginException(AuthenticationException e){
        return new Result(1002,"密码错误！！"+e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public Result NullException(NullPointerException e){
        Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
        logger.info(e.getMessage());
        return new Result(1003,"用户信息失效，请重新登录!!");
    }
}
