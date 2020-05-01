package com.xck.demo.Model;

import lombok.Data;

/**
 * @author 谢朝康
 * @date 2020/4/22 17:33
 */
@Data
public class Roles {
    private int id;
    private String name;

    public Roles() {
    }

    public Roles(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
