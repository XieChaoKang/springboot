package com.xck.demo.controller.infoservicehandler;

import com.alibaba.fastjson.JSON;
import com.xck.demo.model.Score;
import com.xck.demo.model.Stu_course;
import com.xck.demo.service.Info_Service.Info_ServiceImpl.ScoreByIdAndCodeImpl;
import com.xck.demo.service.Info_Service.Info_ServiceImpl.TestServiceImpl;
import com.xck.demo.shiro.util.JwtUtil;
import com.xck.demo.util.LayerResult;
import com.xck.demo.util.RedisUtil;
import com.xck.demo.vo.Stu_test;
import com.xck.demo.util.TestResult;
import com.xck.demo.constant.RedisConstant;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 谢朝康
 * @date 2020/4/11 1:03
 * 查询考试信息
 */
@RestController
public class StudentTest {

    @Autowired
    TestServiceImpl testService;

    @Autowired
    ScoreByIdAndCodeImpl scoreByIdAndCodeImpl;

    @RequestMapping("/sel_test")
    public JSON selTest1(HttpServletRequest servletRequest){
        LoggerFactory.getLogger(StudentTest.class).info("考试到达！！");
        String token = servletRequest.getHeader("AccessToken");
        System.out.println("获取到token:"+token+"  id:"+ JwtUtil.getId(token));
        String id = JwtUtil.getId(token);
        String key = RedisConstant.TEST_PREFIX+id;

        if (RedisUtil.get(key) != null){
            Object object =  RedisUtil.get(key);
            assert object != null;
            return LayerResult.getJson((List) object);
        }
        else {
            List<Stu_course> stuCourses = testService.sel_test(Integer.parseInt(id));
            for (int i = 0; i < stuCourses.size(); i++) {
                Score score = scoreByIdAndCodeImpl.getScore(stuCourses.get(i).getStu_id(), stuCourses.get(i).getCourse_code());
                if (null != score) {
                    stuCourses.remove(i);
                    /**
                     * 索引需要减一
                     * 否则会异常：ConcurrentModificationException 并发修改异常
                     * */
                    i--;
                }
            }
            String name = testService.sel_name(Integer.parseInt(id));
            List<Stu_test> stuTests;
            stuTests = TestResult.result(stuCourses, name);
            RedisUtil.set(key,stuTests,2, TimeUnit.DAYS);
            return LayerResult.getJson(stuTests);
        }
    }
}
