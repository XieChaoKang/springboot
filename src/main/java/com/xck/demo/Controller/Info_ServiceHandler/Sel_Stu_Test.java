package com.xck.demo.Controller.Info_ServiceHandler;

import com.alibaba.fastjson.JSON;
import com.xck.demo.Model.Score;
import com.xck.demo.Model.Stu_course;
import com.xck.demo.Model.user_info;
import com.xck.demo.Service.Info_Service.Info_ServiceImpl.ScoreByIdAndCodeImpl;
import com.xck.demo.Service.Info_Service.Info_ServiceImpl.Sel_TestServiceImpl;
import com.xck.demo.Util.LayerResult;
import com.xck.demo.VO.Stu_test;
import com.xck.demo.Util.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/11 1:03
 * 查询考试信息
 */
@RestController
public class Sel_Stu_Test {

    @Autowired
    Sel_TestServiceImpl testService;

    @Autowired
    ScoreByIdAndCodeImpl scoreByIdAndCodeImpl;

    @RequestMapping("/sel_test")
    public JSON sel_test1(HttpServletRequest request){
        user_info user_info = (user_info) request.getSession().getAttribute("student");
        List<Stu_course> stu_courses = testService.sel_test(user_info.getId());
        for (int i =0;i<stu_courses.size();i++){
            Score score = scoreByIdAndCodeImpl.getScore(stu_courses.get(i).getStu_id(),stu_courses.get(i).getCourse_code());
            if (null != score){
                stu_courses.remove(i);
                /**
                 * 索引需要减一
                 * 否则会异常：ConcurrentModificationException 并发修改异常
                 * */
                i--;
            }
        }
        String name = testService.sel_name(user_info.getId());
        List<Stu_test> stu_tests ;
        stu_tests = TestResult.result(stu_courses,name);
        return LayerResult.getJson(stu_tests);
    }
}
