package com.xck.demo.Model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 谢朝康
 * @date 2020/4/22 17:35
 */
@Data
public class Permission implements Serializable{

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;

    public Permission() {
    }

    public Permission(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
