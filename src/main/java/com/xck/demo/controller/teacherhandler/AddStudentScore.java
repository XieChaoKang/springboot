package com.xck.demo.controller.teacherhandler;

import com.xck.demo.model.Score;
import com.xck.demo.service.Tea_Service.Tea_ServiceImpl.ModifyStuScoreImpl;
import com.xck.demo.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 谢朝康
 * @date 2020/4/28 21:47
 * 老师发布学生成绩数据接口
 */
@Api(tags = "老师发布成绩")
@RestController
public class AddStudentScore {

    @Autowired
    ModifyStuScoreImpl modifyStuScoreImpl;

    @RequestMapping("/add_score")
    public Result addStuScore(Score score){
        int i = 0;
        int scoreMax = 100;
        if(score.getScore() < scoreMax && score.getScore() > 0) {
            i = modifyStuScoreImpl.AddStuScore(score);
        }
        if (i > 0) {
            return new Result(234, "发布学生成绩成功！");
        } else {
            return new Result(123, "发布学生成绩失败！");
        }
    }
}
