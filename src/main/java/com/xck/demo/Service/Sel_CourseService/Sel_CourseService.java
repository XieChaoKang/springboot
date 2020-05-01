package com.xck.demo.Service.Sel_CourseService;

import com.xck.demo.Model.Elective_courses;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/16 15:34
 */
public interface Sel_CourseService {
    List<Elective_courses> sel_ele(String Course_if,int stu_id);

}
