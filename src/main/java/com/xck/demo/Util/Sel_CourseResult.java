package com.xck.demo.Util;

import com.xck.demo.Model.Course;
import com.xck.demo.Model.Elective_courses;
import com.xck.demo.VO.Sel_CourseVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/16 15:54
 * 查询选修课程信息工具类
 */
public class Sel_CourseResult {

    public static List<Sel_CourseVO> result1(List<Elective_courses> coursesList){
        List<Sel_CourseVO> list = new ArrayList<>();
        for (Elective_courses elective_courses : coursesList){
            Course course = elective_courses.getCourse();
            Sel_CourseVO courseVO = new Sel_CourseVO(course.getCourse_code(),course.getCourse_name(),elective_courses.getCourse_capacity(),course.getCourse_teacher(),course.getCourse_location(),course.getCourse_time(),course.getCredit(),course.getCourse_nature());
            list.add(courseVO);
        }
        return list;
    }
}
