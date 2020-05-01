package com.xck.demo.Controller.TeacherHandler;

import com.xck.demo.Model.Score;
import com.xck.demo.Service.Tea_Service.Tea_ServiceImpl.ModifyStuScoreImpl;
import com.xck.demo.Util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 谢朝康
 * @date 2020/4/28 21:47
 * 老师发布学生成绩数据接口
 */
@RestController
public class AddStu_Score {

    @Autowired
    ModifyStuScoreImpl modifyStuScoreImpl;

    @RequestMapping("/add_score")
    public Result addStu_Score(Score score){
        int i = modifyStuScoreImpl.AddStuScore(score);
        if (i > 0) {
            return new Result(234, "发布学生成绩成功！");
        } else {
            return new Result(123, "发布学生成绩失败！");
        }
    }
}
