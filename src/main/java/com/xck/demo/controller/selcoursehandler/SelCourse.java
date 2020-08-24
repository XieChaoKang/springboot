package com.xck.demo.controller.selcoursehandler;

import com.alibaba.fastjson.JSON;
import com.xck.demo.model.Elective_courses;
import com.xck.demo.service.Sel_CourseService.Sel_CourseServiceImpl.SelCourseServiceImpl;
import com.xck.demo.shiro.util.JwtUtil;
import com.xck.demo.util.LayerResult;
import com.xck.demo.util.Sel_CourseResult;
import com.xck.demo.vo.Sel_CourseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/16 15:37
 * 查询选课课程信息
 */
@Api("学生查看选课课程信息")
@RestController
public class SelCourse {

    @Autowired
    SelCourseServiceImpl selCourseService;

    @ApiOperation("学生查看选课课程信息接口，需要把token放在请求头")
    @RequestMapping("/sel_course")
    public JSON selCourse(HttpServletRequest servletRequest){

        return getJson(servletRequest, "A");
    }

    @RequestMapping("/sel_course1")
    public JSON selCourse1(HttpServletRequest servletRequest){

        return getJson(servletRequest, "B");
    }

    private JSON getJson(HttpServletRequest servletRequest, String type) {
        String token = servletRequest.getHeader("AccessToken");
        System.out.println("获取到token:"+token+"  id:"+ JwtUtil.getId(token));
        String id = JwtUtil.getId(token);
        assert id != null;
        List<Elective_courses> list = selCourseService.sel_ele(type, Integer.parseInt(id));
        List<Sel_CourseVO> list1 = Sel_CourseResult.result1(list);
        return LayerResult.getJson(list1);
    }
}
