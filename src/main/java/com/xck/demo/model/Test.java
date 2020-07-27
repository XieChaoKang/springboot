package com.xck.demo.model;


import lombok.Data;

import java.io.Serializable;

/**
 * @author 谢朝康
 * @date 2020/4/4 14:13
 * 考试信息
 */
@Data
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    private int Course_code;//课程代码
    private String Course_name;//课程名
   // private String Student_name;//学生名
    private String time;//考试时间
    private String location;//考试地点
    private int Seat_num;//座位号
   // private int Student_id;//学生号

    public Test() {
    }

    public Test(int course_code, String course_name, String time, String location, int seat_num) {
        Course_code = course_code;
        Course_name = course_name;
        this.time = time;
        this.location = location;
        Seat_num = seat_num;
    }
}
