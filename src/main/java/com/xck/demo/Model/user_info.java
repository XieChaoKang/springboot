package com.xck.demo.Model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author 谢朝康
 * @date 2020/4/4 14:17
 * 用户信息
 */
@Data
public class user_info {
    @NotNull(message = "id不能为空！！！")
    private int id;
    private String password;
    @NotNull(message = "姓名不能为空")
    private String name;
    @NotNull(message = "性别不能为空")
    private String gender;
    @NotNull(message = "生日不能为空")
    private String birthday;
    @NotNull(message = "民族不能为空")
    private String nationality;//民族
    @NotNull(message = "身份证号码不能为空")
    @Size(min = 18,max = 18,message = "身份证号码必须是18位")
    private String ID_card;
    @NotNull(message = "政治面貌不能为空")
    private String political_status;//政治面貌
    @NotNull(message = "住址不能为空")
    private String address;
    @NotNull(message = "电话号码不能为空")
    private String phone;
    @NotNull(message = "院系不能为空")
    private String department;//所属院系
    @NotNull(message = "专业不能为空")
    private String Major_name;//专业名
    @NotNull(message = "班级不能为空")
    private String Administrative_class;//专业班级
    private String salt; //盐

    public user_info() {
    }

    public user_info(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public user_info(String password, String salt) {
        this.password = password;
        this.salt = salt;
    }

    public user_info(int id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public user_info(int id, String password, String name, String salt) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.salt = salt;
    }

    public user_info(String name) {
        this.name = name;
    }

    public user_info(@NotNull(message = "id不能为空！！！") int id, @NotNull(message = "姓名不能为空") String name, @NotNull(message = "性别不能为空") String gender, @NotNull(message = "生日不能为空") String birthday, @NotNull(message = "民族不能为空") String nationality, @NotNull(message = "身份证号码不能为空") @Size(min = 18, max = 18, message = "身份证号码必须是18位") String ID_card, @NotNull(message = "政治面貌不能为空") String political_status, @NotNull(message = "住址不能为空") String address, @NotNull(message = "电话号码不能为空") String phone, @NotNull(message = "院系不能为空") String department, @NotNull(message = "专业不能为空") String major_name, @NotNull(message = "班级不能为空") String administrative_class) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.nationality = nationality;
        this.ID_card = ID_card;
        this.political_status = political_status;
        this.address = address;
        this.phone = phone;
        this.department = department;
        Major_name = major_name;
        Administrative_class = administrative_class;
    }

    public user_info(int id, String password, String name, String gender, String birthday, String nationality, String ID_card, String political_status, String address, String phone, String department, String major_name, String administrative_class, String salt) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.nationality = nationality;
        this.ID_card = ID_card;
        this.political_status = political_status;
        this.address = address;
        this.phone = phone;
        this.department = department;
        this.Major_name = major_name;
        this.Administrative_class = administrative_class;
        this.salt = salt;
    }
}
