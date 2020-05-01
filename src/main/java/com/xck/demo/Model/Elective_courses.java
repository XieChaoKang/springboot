package com.xck.demo.Model;

import lombok.Data;

/**
 * @author 谢朝康
 * @date 2020/4/4 13:27
 * 选课信息
 */
@Data
public class Elective_courses {
    private int Course_code;//课程代码
    private int Course_capacity;//课程容量
    private String Course_if;//课程类型 A:全校选修文化课 B：选修体育课
    private Course course;//课程类 连表查询

    public Elective_courses() {
    }

    public Elective_courses(int course_code, int course_capacity, String course_if) {
        Course_code = course_code;
        Course_capacity = course_capacity;
        Course_if = course_if;
    }

    public Elective_courses(int course_code, int course_capacity, String course_if, Course course) {
        Course_code = course_code;
        Course_capacity = course_capacity;
        Course_if = course_if;
        this.course = course;
    }
}
