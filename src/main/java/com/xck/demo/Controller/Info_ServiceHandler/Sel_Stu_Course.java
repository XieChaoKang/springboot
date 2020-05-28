package com.xck.demo.Controller.Info_ServiceHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xck.demo.Model.Course;
import com.xck.demo.Service.Info_Service.Info_ServiceImpl.Sel_Stu_CourseServiceImpl;
import com.xck.demo.Shiro.util.JwtUtil;
import com.xck.demo.Util.LayerResult;
import com.xck.demo.Util.RedisUtil;
import com.xck.demo.constant.RedisConstant;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RestController
public class Sel_Stu_Course {

   // @Qualifier("ListredisTempate")
    @Autowired
    private RedisTemplate<String,Object> ListredisTemplate;

    @Autowired
    private Sel_Stu_CourseServiceImpl stuCourseService;

    @RequestMapping("/get_course")
    public JSON sel_course(HttpServletRequest servletRequest){
        LoggerFactory.getLogger(Sel_Stu_Course.class).info("课程到达！！");
        String token = servletRequest.getHeader("AccessToken");
        System.out.println("获取到token:"+token+"  id:"+ JwtUtil.getId(token));
        String id = JwtUtil.getId(token);
        String course_class = JwtUtil.getClass(token);
        String key = RedisConstant.COURSE_PREFIX+id;

        if (id != null && course_class != null) {
            if (RedisUtil.get(key) != null) {
                LoggerFactory.getLogger(Sel_Stu_Course.class).info("key");
                Object object = RedisUtil.get(key);
                return LayerResult.getJson((List) object);
            } else {
                System.out.println("班级：" + course_class + "\n");
                List<Course> courseList = stuCourseService.sel_course(Integer.parseInt(id), course_class);
                System.out.println(courseList);
                //设置过期时间为2天
                RedisUtil.set(key, courseList, 2, TimeUnit.DAYS);
                return LayerResult.getJson(courseList);
            }
        }
        else return LayerResult.getJson(new ArrayList());
    }
}
