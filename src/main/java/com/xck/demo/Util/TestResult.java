package com.xck.demo.Util;

import com.xck.demo.Model.Stu_course;
import com.xck.demo.VO.Stu_test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/11 0:38
 * 解析查询考试信息时返回信息，并封装成对象，以便转化为layui能识别的json数据格式
 */
public class TestResult {

    public static List<Stu_test> result(List<Stu_course> stu_courses, String stu_name){
        List<Stu_test> stuTests = new ArrayList<>();
        for (Stu_course stu_course : stu_courses){
            if (stu_course.getTest() != null) {
                Stu_test stuTest = new Stu_test(stu_course.getStu_id(), stu_course.getCourse_code(), stu_course.getTest().getCourse_name(), stu_name, stu_course.getTest().getTime(), stu_course.getTest().getLocation(), stu_course.getTest().getSeat_num());
                stuTests.add(stuTest);
            }

        }
        return stuTests;
    }
}
