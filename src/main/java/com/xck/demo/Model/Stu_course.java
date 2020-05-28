package com.xck.demo.Model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/4 14:10
 * 学生选择的课程
 */
@Data
public class Stu_course implements Serializable {

    private static final long serialVersionUID = 1L;

    private int stu_id;//学生id
    private int course_code;//课程代码
    private Test test;
    private Score score;

    public Stu_course() {
    }

    public Stu_course(int stu_id, int course_code) {
        this.stu_id = stu_id;
        this.course_code = course_code;
    }

    public Stu_course(int stu_id, int course_code,Test test) {
        this.stu_id = stu_id;
        this.course_code = course_code;
        this.test = test;
    }

    public Stu_course(int stu_id, int course_code, Score score) {
        this.stu_id = stu_id;
        this.course_code = course_code;
        this.score = score;
    }

}
