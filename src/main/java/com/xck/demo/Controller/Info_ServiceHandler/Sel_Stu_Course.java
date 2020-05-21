package com.xck.demo.Controller.Info_ServiceHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xck.demo.Model.Course;
import com.xck.demo.Service.Info_Service.Info_ServiceImpl.Sel_Stu_CourseServiceImpl;
import com.xck.demo.Util.LayerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public JSON sel_course(@RequestParam("id") int id,@RequestParam("class1") String course_class){

        String key = "course::"+id;

        boolean redisKey = ListredisTemplate.hasKey(key);
        if (redisKey){
            Object object =  ListredisTemplate.opsForValue().get(key);
            //List<Course> list = new ArrayList<>();
            //Course course = JSONObject.parseObject((String) object, Course.class);
            //list = JSONObject.parseArray((String) object,Course.class);
            //System.out.println("redis:"+ object);
            //System.out.println((List)object);
            return LayerResult.getJson((List) object);
        }
        else {
            System.out.println("班级：" + course_class + "\n");
            List<Course> courseList = stuCourseService.sel_course(id, course_class);
            System.out.println(courseList);
            //设置过期时间为2天
            ListredisTemplate.opsForValue().set(key,courseList,2, TimeUnit.DAYS);
            return LayerResult.getJson(courseList);
        }
    }
}
