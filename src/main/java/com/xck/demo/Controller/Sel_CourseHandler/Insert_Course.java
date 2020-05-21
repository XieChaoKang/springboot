package com.xck.demo.Controller.Sel_CourseHandler;

import com.xck.demo.Model.Elective_courses;
import com.xck.demo.Model.Stu_course;
import com.xck.demo.Model.user_info;
import com.xck.demo.Service.Sel_CourseService.Sel_CourseServiceImpl.Get_CapacityServiceImpl;
import com.xck.demo.Service.Sel_CourseService.Sel_CourseServiceImpl.In_CourseServiceImpl;
import com.xck.demo.Service.Sel_CourseService.Sel_CourseServiceImpl.Up_CapacityServiceImpl;
import com.xck.demo.Util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/16 17:40
 * 学生选择课程提交入口
 */
@RestController
public class Insert_Course {

    Logger logger = LoggerFactory.getLogger(Insert_Course.class);

    @Autowired
    In_CourseServiceImpl in_courseService;

    @Autowired
    Get_CapacityServiceImpl get_capacityService;

    @Autowired
    Up_CapacityServiceImpl upCapacityService;

    @RequestMapping("/in_course")
    public Result test(@RequestParam("course_codes") String course_codes,@RequestParam("id") int id) {
        logger.info("访问到达");
        List<Stu_course> list = new ArrayList<>();
        List<Elective_courses> list1 = new ArrayList<>();
        String course_code[] = course_codes.split(",");
        for (int i = 0; i < course_code.length; i++) {
            int capacity = get_capacityService.getCourse_capacity(Integer.valueOf(course_code[i]));
            if (capacity > 0) {
                Stu_course stu_course = new Stu_course(id, Integer.valueOf(course_code[i]));
                // System.out.println(course_code[i]);
                list.add(stu_course);
                Elective_courses elective_courses = new Elective_courses(Integer.valueOf(course_code[i]),capacity-1);
                list1.add(elective_courses);
            }
        }
        int i = in_courseService.add_course(list);
        int j = upCapacityService.upCourse_capacity(list1);
        if (i != 0 && j != 0) {
            logger.info("更新："+j);
            return new Result(233, "抢课成功了");
        }
        else {
            return new Result(133,"抢课失败了");
        }
    }

}
