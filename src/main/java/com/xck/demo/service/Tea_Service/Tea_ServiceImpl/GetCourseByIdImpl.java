package com.xck.demo.service.Tea_Service.Tea_ServiceImpl;

import com.xck.demo.mapper.TeacherMapper;
import com.xck.demo.model.Course;
import com.xck.demo.service.Tea_Service.GetCourseById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/28 10:57
 */
@Service
public class GetCourseByIdImpl implements GetCourseById {

    @Autowired
    TeacherMapper teacherMapper;

    /**
     * 老师根据自己的id查询所当任课程的信息
     * @param tea_id
     * @return List
     * */
    @Override
    public List<Course> getCourseById(int tea_id) {
        return teacherMapper.getCourseById(tea_id);
    }
}
