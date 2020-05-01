package com.xck.demo.Controller.Sel_CourseHandler;

import com.xck.demo.Model.Stu_course;
import com.xck.demo.Model.user_info;
import com.xck.demo.Service.Sel_CourseService.Sel_CourseServiceImpl.In_CourseServiceImpl;
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

    @RequestMapping("/in_course")
    public Result test(@RequestParam("course_codes") String course_codes, HttpServletRequest request) {
        logger.info("访问到达");
        user_info user_info = (user_info) request.getSession().getAttribute("student");
        List<Stu_course> list = new ArrayList<>();
        String course_code[] = course_codes.split(",");
        for (int i = 0; i < course_code.length; i++) {
            Stu_course stu_course = new Stu_course(user_info.getId(), Integer.valueOf(course_code[i]));
            // System.out.println(course_code[i]);
            list.add(stu_course);
        }
        int i = in_courseService.add_course(list);
        if (i != 0) {
            return new Result(233, "抢课成功了");
        }
        else {
            return new Result(133,"抢课失败了");
        }
    }

}
