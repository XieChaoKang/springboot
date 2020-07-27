package com.xck.demo.controller.teacherhandler;

import com.xck.demo.model.Score;
import com.xck.demo.service.Tea_Service.Tea_ServiceImpl.UpStuScoreImpl;
import com.xck.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 谢朝康
 * @date 2020/4/28 23:14
 * 老师更改学生成绩接口
 */
@RestController
public class UpdateStudentScore {

    @Autowired
    UpStuScoreImpl upStuScoreImpl;

    @RequestMapping("up_score")
    public Result upStuScore(Score score){
        int i = 0;
        int scoreMax = 100;
        if (score.getScore() < scoreMax && score.getScore() > 0) {
            i = upStuScoreImpl.upStuScore(score.getCourse_code(), score.getScore());
        }
        if (i > 0){
            return new Result(243,"更改学生成绩成功！");
        }
        else {
            return new Result(134,"更改学生成绩失败！");
        }
    }
}
