package com.xck.demo.VO;

import lombok.Data;

/**
 * @author 谢朝康
 * @date 2020/4/11 0:39
 * 封装考试信息所需类
 */
@Data
public class Stu_test {

    private int stu_id;//学生id
    private int course_code;//课程代码
    private String Course_name;//课程名
    private String Student_name;//学生名
    private String time;//考试时间
    private String location;//考试地点
    private int Seat_num;//座位号

    public Stu_test() {
    }

    public Stu_test(int stu_id, int course_code, String course_name, String student_name, String time, String location, int seat_num) {
        this.stu_id = stu_id;
        this.course_code = course_code;
        Course_name = course_name;
        Student_name = student_name;
        this.time = time;
        this.location = location;
        Seat_num = seat_num;
    }
}
