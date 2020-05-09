package com.xck.demo.Service.Sel_CourseService.Sel_CourseServiceImpl;

import com.xck.demo.Mapper.Select_CourseMapper;
import com.xck.demo.Model.Elective_courses;
import com.xck.demo.Service.Sel_CourseService.Up_CapacityService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/5/9 9:40
 */
@Service
public class Up_CapacityServiceImpl implements Up_CapacityService {

    @Autowired
    Select_CourseMapper selectCourseMapper;

    /**
     * 用户选课后更改课程容量
     * */
    @Override
    public int upCourse_capacity(List< Elective_courses> list) {
        return selectCourseMapper.UpCourse_capacity(list);
    }
}