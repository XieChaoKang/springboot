package com.xck.demo.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author 谢朝康
 * @date 2020/4/25 21:43
 */
@Data
public class InfoVO {
    private int id;
    private String name;
    private String gender;
    private String birthday;
    private String nationality;//民族
    private String ID_card;
    private String political_status;//政治面貌
    private String address;
    private String phone;
    private String department;//所属院系
    private String Major_name;//专业名
    private String Administrative_class;//专业班级

    public InfoVO(int id, String name, String gender, String birthday, String nationality, String ID_card, String political_status, String address, String phone, String department, String major_name, String administrative_class) {
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
}
