package com.xck.demo.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author 谢朝康
 * @date 2020/4/16 18:00
 * 统一格式返回工具类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable{
    private Integer code;
    private String message;
    private T data;

    public Result(Integer code, String massage) {
        this.code = code;
        this.message = massage;
    }

    public static Result success(Integer code, String message, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

}
