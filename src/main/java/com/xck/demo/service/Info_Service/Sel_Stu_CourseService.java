package com.xck.demo.service.Info_Service;

import com.xck.demo.model.Course;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/23 17:45
 */
public interface Sel_Stu_CourseService {
    List<Course> sel_course(int id,String course_class);
}
