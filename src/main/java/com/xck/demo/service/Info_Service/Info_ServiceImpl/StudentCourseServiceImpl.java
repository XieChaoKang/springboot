package com.xck.demo.service.Info_Service.Info_ServiceImpl;

import com.xck.demo.Mapper.Info_ServiceMapper;
import com.xck.demo.model.Course;
import com.xck.demo.service.Info_Service.Sel_Stu_CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/23 17:46
 */
@Service
public class StudentCourseServiceImpl implements Sel_Stu_CourseService {

    @Autowired
    Info_ServiceMapper info_serviceMapper;

    @Override
    public List<Course> sel_course(int id,String course_class) {
       // System.out.println(info_serviceMapper.sel_course(19,"计算机科学与技术二班"));
        return info_serviceMapper.sel_course(id,course_class);
    }
}
