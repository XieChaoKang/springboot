package com.xck.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 谢朝康
 * @date 2020/4/22 17:33
 */
@Data
public class Roles implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;

    public Roles() {
    }

    public Roles(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
