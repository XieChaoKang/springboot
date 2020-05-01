package com.xck.demo.Service.Tea_Service.Tea_ServiceImpl;

import com.xck.demo.Mapper.TeacherMapper;
import com.xck.demo.Service.Tea_Service.UpStuScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谢朝康
 * @date 2020/4/28 22:55
 */
@Service
public class UpStuScoreImpl implements UpStuScore {

    @Autowired
    TeacherMapper teacherMapper;

    /**
     * 老师修改学生成绩
     * @param course_code //
     * @param score //
     * @return int
     * */
    @Override
    public int upStuScore(int course_code,int score) {
        return teacherMapper.upStuScore(course_code,score);
    }
}
