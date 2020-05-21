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
    private Object data;
    /*private int id;
    private String name;*/

    public Result(Integer code, String massge) {
        this.code = code;
        this.message = massge;
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /*public Result(Integer code, String message,int id, String name) {
        this.code = code;
        this.message = message;
        this.id = id;
        this.name = name;
    }*/
}
