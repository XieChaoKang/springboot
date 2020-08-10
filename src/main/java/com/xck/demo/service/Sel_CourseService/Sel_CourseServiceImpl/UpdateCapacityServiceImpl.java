package com.xck.demo.service.Sel_CourseService.Sel_CourseServiceImpl;

import com.xck.demo.mapper.Select_CourseMapper;
import com.xck.demo.model.Elective_courses;
import com.xck.demo.service.Sel_CourseService.Up_CapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/5/9 9:40
 */
@Service
public class UpdateCapacityServiceImpl implements Up_CapacityService {

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
