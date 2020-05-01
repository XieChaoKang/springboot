package com.xck.demo.Util;

import com.xck.demo.Model.Score;
import com.xck.demo.Model.Stu_course;
import com.xck.demo.VO.Stu_ScoreVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/12 2:18
 * 解析查询成绩信息时返回信息，并封装成对象，以便转化为layui能识别的json数据格式
 */
public class ScoreResult {

    //封装及格的成绩
    public static List<Stu_ScoreVO> Score_Result(List<Score>scores){
        List<Stu_ScoreVO> list = new ArrayList<>();
        for (Score score : scores){
                Stu_ScoreVO score1 = new Stu_ScoreVO(score.getCourse_code(),score.getCourse().getCourse_name(),score.getScore(),score.getCourse().getCredit());
                if (score.getScore() > 60) {
                    list.add(score1);
                }
        }
        return list;
    }

    //封装不及格的成绩
    public static List<Stu_ScoreVO> Score_Result1(List<Score>scores){
        List<Stu_ScoreVO> list = new ArrayList<>();
        for (Score score : scores){
            Stu_ScoreVO score1 = new Stu_ScoreVO(score.getCourse_code(),score.getCourse().getCourse_name(),score.getScore(),score.getCourse().getCredit());
            if (score.getScore() < 60) {
                list.add(score1);
            }
        }
        return list;
    }

}
