package com.xck.demo.vo;

import lombok.Data;

/**
 * @author 谢朝康
 * @date 2020/4/28 11:30
 * 老师获取学生成绩返回信息实体类
 */
@Data
public class Tea_ScoreVO {
    private int stu_id;
    private String stu_name;
    private int course_code;
    private String course_name;
    private int score;

    public Tea_ScoreVO(int stu_id, String stu_name, int course_code, String course_name) {
        this.stu_id = stu_id;
        this.stu_name = stu_name;
        this.course_code = course_code;
        this.course_name = course_name;
    }

    public Tea_ScoreVO(int stu_id, String stu_name, int course_code, String course_name, int score) {
        this.stu_id = stu_id;
        this.stu_name = stu_name;
        this.course_code = course_code;
        this.course_name = course_name;
        this.score = score;
    }
}
