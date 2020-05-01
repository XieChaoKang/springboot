package com.xck.demo.Util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 谢朝康
 * @date 2020/4/16 18:00
 * 统一格式返回工具类
 */
@Data
public class Result implements Serializable{
    private Integer code;
    private String message;

    public Result(Integer code, String massge) {
        this.code = code;
        this.message = massge;
    }
}
