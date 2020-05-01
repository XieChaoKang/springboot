package com.xck.demo.VO;

import lombok.Data;

/**
 * @author 谢朝康
 * @date 2020/4/16 21:30
 */
@Data
public class Stu_ScoreVO {
    private int Course_code;
    private String Course_name;//课程名
    private int score;
    private float credit;//学分

    public Stu_ScoreVO(int course_code, String course_name, int score, float credit) {
        Course_code = course_code;
        Course_name = course_name;
        this.score = score;
        this.credit = credit;
    }
}
