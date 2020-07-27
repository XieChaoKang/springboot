package com.xck.demo.model;


import lombok.Data;

import java.io.Serializable;

/**
 * @author 谢朝康
 * @date 2020/4/4 13:18
 * 课程信息
 */
@Data
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int Course_code;//课程代码
    private String Course_name;//课程名
    private String Course_teacher;//任课老师
    private String Course_location;//上课地点
    private String Course_time;//上课时间
    private float credit;//学分
    private String Course_nature;//课程性质
    private String course_class;

    public Course() {
    }

    public Course(String course_name, float credit) {
        Course_name = course_name;
        this.credit = credit;
    }

    public Course(int course_code, String course_name) {
        Course_code = course_code;
        Course_name = course_name;
    }

    public Course(int id, int course_code, String course_name, String course_teacher, String course_location, String course_time, float credit, String course_nature, String course_class) {
        this.id = id;
        Course_code = course_code;
        Course_name = course_name;
        Course_teacher = course_teacher;
        Course_location = course_location;
        Course_time = course_time;
        this.credit = credit;
        Course_nature = course_nature;
        this.course_class = course_class;
    }
}
