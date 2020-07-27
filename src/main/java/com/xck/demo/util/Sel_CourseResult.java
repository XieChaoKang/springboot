package com.xck.demo.util;

import com.xck.demo.model.Course;
import com.xck.demo.model.Elective_courses;
import com.xck.demo.vo.Sel_CourseVO;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

/**
 * @author 谢朝康
 * @date 2020/4/16 15:54
 * 查询选修课程信息工具类
 */
public class Sel_CourseResult {

    public static List<Sel_CourseVO> result1(List<Elective_courses> coursesList){
        Logger logger = LoggerFactory.getLogger(Sel_CourseResult.class);
        List<Sel_CourseVO> list = new ArrayList<>();
        for (Elective_courses elective_courses : coursesList){
            logger.info("容量："+elective_courses.getCourse_capacity());
            if(elective_courses.getCourse_capacity() > 0) {
                Course course = elective_courses.getCourse();
                Sel_CourseVO courseVO = new Sel_CourseVO(course.getCourse_code(), course.getCourse_name(), elective_courses.getCourse_capacity(), course.getCourse_teacher(), course.getCourse_location(), course.getCourse_time(), course.getCredit(), course.getCourse_nature());
                list.add(courseVO);
            }
        }
        return list;
    }
}
