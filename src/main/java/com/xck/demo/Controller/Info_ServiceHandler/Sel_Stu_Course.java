package com.xck.demo.Controller.Info_ServiceHandler;

import com.alibaba.fastjson.JSON;
import com.xck.demo.Model.Course;
import com.xck.demo.Model.user_info;
import com.xck.demo.Service.Info_Service.Info_ServiceImpl.Sel_Stu_CourseServiceImpl;
import com.xck.demo.Util.LayerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/23 17:45
 * 学生查询课表入口
 */
@RestController
public class Sel_Stu_Course {

    @Autowired
    private Sel_Stu_CourseServiceImpl stuCourseService;

    @RequestMapping("/get_course")
    public JSON sel_course(HttpServletRequest request){
        user_info user_info = (user_info) request.getSession().getAttribute("student");
        List<Course> courseList = stuCourseService.sel_course(user_info.getId());
        return LayerResult.getJson(courseList);
    }
}
