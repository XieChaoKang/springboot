package com.xck.demo.service.Sel_CourseService.Sel_CourseServiceImpl;

import com.xck.demo.Mapper.Select_CourseMapper;
import com.xck.demo.model.Stu_course;
import com.xck.demo.service.Sel_CourseService.In_CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/16 17:53
 */
@Service
public class InsertCourseServiceImpl implements In_CourseService {

    @Autowired
    Select_CourseMapper select_courseMapper;

    /**
     * 批量将学生所选择课程信息更新到数据库中
     * @param list
     * @return 0或1
     * */
    @Override
    public int add_course(List<Stu_course> list) {
        return select_courseMapper.Add_Sel_Course(list);
    }
}
