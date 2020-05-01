package com.xck.demo.Model;

import lombok.Data;

/**
 * @author 谢朝康
 * @date 2020/4/22 17:35
 */
@Data
public class Permission {
    private int id;
    private String name;

    public Permission() {
    }

    public Permission(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
