package com.xck.demo.Controller.Sel_CourseHandler;

import com.alibaba.fastjson.JSON;
import com.xck.demo.Model.Elective_courses;
import com.xck.demo.Model.user_info;
import com.xck.demo.Service.Sel_CourseService.Sel_CourseServiceImpl.Sel_CourseServiceImpl;
import com.xck.demo.Util.LayerResult;
import com.xck.demo.Util.Sel_CourseResult;
import com.xck.demo.VO.Sel_CourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/16 15:37
 * 查询选课课程信息
 */
@RestController
public class Sel_Course {

    @Autowired
    Sel_CourseServiceImpl sel_courseService;

    @RequestMapping("/sel_course")
    public JSON sel_course(@RequestParam("id") int id){

        List<Elective_courses> list = sel_courseService.sel_ele("A", id);
        List<Sel_CourseVO> list1 = Sel_CourseResult.result1(list);
        return LayerResult.getJson(list1);
    }

    @RequestMapping("/sel_course1")
    public JSON sel_course1(@RequestParam("id") int id){

        List<Elective_courses> list = sel_courseService.sel_ele("B", id);
        List<Sel_CourseVO> list1 = Sel_CourseResult.result1(list);
        return LayerResult.getJson(list1);
    }
}
