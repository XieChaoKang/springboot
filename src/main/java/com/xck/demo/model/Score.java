package com.xck.demo.model;

import lombok.Data;

/**
 * @author 谢朝康
 * @date 2020/4/4 13:31
 * 成绩
 */
@Data
public class Score {
    private int Course_code;
    private String school_year;
    private int stu_id;
    private int score;
    private Course course;

    public Score() {
    }

    public Score(int score) {
        this.score = score;
    }

    public Score(int course_code, int stu_id, int score) {
        this.Course_code = course_code;
        this.stu_id = stu_id;
        this.score = score;
    }

    public Score(int course_code, String school_year, int stu_id, int score, Course course) {
        Course_code = course_code;
        this.school_year = school_year;
        this.stu_id = stu_id;
        this.score = score;
        this.course = course;
    }
}
