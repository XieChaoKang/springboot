package com.xck.demo.controller.infoservicehandler;

import com.alibaba.fastjson.JSON;
import com.xck.demo.model.Course;
import com.xck.demo.service.Info_Service.Info_ServiceImpl.StudentCourseServiceImpl;
import com.xck.demo.shiro.util.JwtUtil;
import com.xck.demo.util.LayerResult;
import com.xck.demo.util.RedisUtil;
import com.xck.demo.constant.RedisConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 谢朝康
 * @date 2020/4/23 17:45
 * 学生查询课表入口
 */
@Api(tags = "学生查询课表")
@RestController
public class StudentCourse {

    @Autowired
    private StudentCourseServiceImpl stuCourseService;

    @ApiOperation("学生查询课表接口，需要把token放在请求头")
    @RequestMapping("/get_course")
    public JSON selCourse(HttpServletRequest servletRequest){
        LoggerFactory.getLogger(StudentCourse.class).info("课程到达！！");
        String token = servletRequest.getHeader("AccessToken");
        System.out.println("获取到token:"+token+"  id:"+ JwtUtil.getId(token));
        String id = JwtUtil.getId(token);
        String courseClass = JwtUtil.getClass(token);
        String key = RedisConstant.COURSE_PREFIX+id;

        if (id != null && courseClass != null) {
            if (RedisUtil.get(key) != null) {
                LoggerFactory.getLogger(StudentCourse.class).info("key");
                Object object = RedisUtil.get(key);
                assert object != null;
                return LayerResult.getJson((List) object);
            } else {
                System.out.println("班级：" + courseClass + "\n");
                List<Course> courseList = stuCourseService.sel_course(Integer.parseInt(id), courseClass);
                System.out.println(courseList);
                //设置过期时间为2天
                RedisUtil.set(key, courseList, 2, TimeUnit.DAYS);
                return LayerResult.getJson(courseList);
            }
        }
        else {
            return LayerResult.getJson(new ArrayList());
        }
    }
}
