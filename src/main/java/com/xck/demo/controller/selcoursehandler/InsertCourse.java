package com.xck.demo.controller.selcoursehandler;

import com.xck.demo.model.Elective_courses;
import com.xck.demo.model.Stu_course;
import com.xck.demo.service.Sel_CourseService.Sel_CourseServiceImpl.GetCapacityServiceImpl;
import com.xck.demo.service.Sel_CourseService.Sel_CourseServiceImpl.InsertCourseServiceImpl;
import com.xck.demo.service.Sel_CourseService.Sel_CourseServiceImpl.UpdateCapacityServiceImpl;
import com.xck.demo.util.RedisUtil;
import com.xck.demo.constant.RedisConstant;
import com.xck.demo.shiro.util.JwtUtil;
import com.xck.demo.util.Result;
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
public class InsertCourse {

    Logger logger = LoggerFactory.getLogger(InsertCourse.class);

    @Autowired
    InsertCourseServiceImpl inCourseService;

    @Autowired
    GetCapacityServiceImpl getCapacityService;

    @Autowired
    UpdateCapacityServiceImpl upCapacityService;

    @RequestMapping("/in_course")
    public Result test(@RequestParam("course_codes") String courseCodes,HttpServletRequest servletRequest) {
        logger.info("访问到达");
        String token = servletRequest.getHeader("AccessToken");
        System.out.println("获取到token:"+token+"  id:"+ JwtUtil.getId(token));
        String id = JwtUtil.getId(token);
        String key = RedisConstant.COURSE_PREFIX+id;
        List<Stu_course> list = new ArrayList<>();
        List<Elective_courses> list1 = new ArrayList<>();
        String[] courseCode = courseCodes.split(",");
        for (String aCourseCode : courseCode) {
            int capacity = getCapacityService.getCourse_capacity(Integer.parseInt(aCourseCode));
            if (capacity > 0) {
                assert id != null;
                Stu_course stuCourse = new Stu_course(Integer.parseInt(id), Integer.parseInt(aCourseCode));
                list.add(stuCourse);
                Elective_courses electiveCourses = new Elective_courses(Integer.parseInt(aCourseCode), capacity - 1);
                list1.add(electiveCourses);
            }
        }
        int i = inCourseService.add_course(list);
        int j = upCapacityService.upCourse_capacity(list1);
        if (i != 0 && j != 0) {
            logger.info("更新："+j);
            RedisUtil.delete(key);
            return new Result(233, "抢课成功了");
        }
        else {
            return new Result(133,"抢课失败了");
        }
    }

}
