package com.xck.demo.service.Tea_Service.Tea_ServiceImpl;

import com.xck.demo.mapper.TeacherMapper;
import com.xck.demo.model.Score;
import com.xck.demo.service.Tea_Service.GetScoreByIdAndCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/28 11:27
 */
@Service
public class GetScoreByIdAndCodeImpl implements GetScoreByIdAndCode {

    @Autowired
    TeacherMapper teacherMapper2;

    /**
     * 老师根据课程id和学生id获取成绩
     * @param stu_id ;
     * @param Course_code ;
     * @return List<Score>
     * @exception NullPointerException
     * */
    @Override
    public List<Score> getSocreByIdAndCode(int stu_id, int Course_code) {
        return teacherMapper2.getScoreByIdAndCode(stu_id,Course_code);
    }
}
