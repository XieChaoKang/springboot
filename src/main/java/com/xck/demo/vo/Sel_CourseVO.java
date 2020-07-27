package com.xck.demo.vo;

import lombok.Data;

/**
 * @author 谢朝康
 * @date 2020/4/16 15:58
 * 查询选修课程信息封装类
 */
@Data
public class Sel_CourseVO {
    private int Course_code;//课程代码
    private String Course_name;//课程名
    private int Course_capacity;//课程容量
    private String Course_teacher;//任课老师
    private String Course_location;//上课地点
    private String Course_time;//上课时间
    private float credit;//学分
    private String Course_nature;//课程性质

    public Sel_CourseVO(int course_code, String course_name, int course_capacity, String course_teacher, String course_location, String course_time, float credit, String course_nature) {
        Course_code = course_code;
        Course_name = course_name;
        Course_capacity = course_capacity;
        Course_teacher = course_teacher;
        Course_location = course_location;
        Course_time = course_time;
        this.credit = credit;
        Course_nature = course_nature;
    }
}
