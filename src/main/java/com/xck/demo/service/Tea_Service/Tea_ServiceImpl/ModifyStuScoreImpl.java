package com.xck.demo.service.Tea_Service.Tea_ServiceImpl;

import com.xck.demo.mapper.TeacherMapper;
import com.xck.demo.model.Score;
import com.xck.demo.service.Tea_Service.ModifyStuScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谢朝康
 * @date 2020/4/28 22:35
 */
@Service
public class ModifyStuScoreImpl implements ModifyStuScore {

    @Autowired
    TeacherMapper teacherMapper;

    /**
     * 老师新添加学生成绩
     * @param score //score(course_code,stu_id,score)
     * @return int
     * */
    @Override
    public int AddStuScore(Score score) {
        return teacherMapper.AddStuScore(score);
    }

}
