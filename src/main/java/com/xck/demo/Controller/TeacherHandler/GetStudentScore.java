package com.xck.demo.Controller.TeacherHandler;

import com.alibaba.fastjson.JSON;
import com.xck.demo.Controller.Info_ServiceHandler.Sel_Stu_Course;
import com.xck.demo.Model.Course;
import com.xck.demo.Model.Score;
import com.xck.demo.Model.user_info;
import com.xck.demo.Service.Tea_Service.Tea_ServiceImpl.GetCourseByIdImpl;
import com.xck.demo.Service.Tea_Service.Tea_ServiceImpl.GetScoreByIdAndCodeImpl;
import com.xck.demo.Service.Tea_Service.Tea_ServiceImpl.GetStu_infoByIdImpl;
import com.xck.demo.Shiro.util.JwtUtil;
import com.xck.demo.Util.LayerResult;
import com.xck.demo.VO.Tea_ScoreVO;
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
 * @date 2020/4/28 11:30
 */
@RestController
public class GetStudentScore {

    @Autowired
    GetCourseByIdImpl getCourseByIdImpl;

    @Autowired
    GetScoreByIdAndCodeImpl getScoreByIdAndCode;

    @Autowired
    GetStu_infoByIdImpl getStu_infoById;

    //老师发布成绩使用的学生成绩信息接口
    @RequestMapping("/getStu_Score")
    public JSON getStu_Score(HttpServletRequest servletRequest){
        LoggerFactory.getLogger(GetStudentScore.class).info("老师课程到达！！");
        String token = servletRequest.getHeader("AccessToken");
        System.out.println("获取到token:"+token+"  id:"+ JwtUtil.getId(token));
        String id = JwtUtil.getId(token);
        List<Course> courseList ;
        List<user_info> userInfos ;
        List<Score> scores;
        List<Tea_ScoreVO> scoreVOS = new ArrayList<>();
        courseList = getCourseByIdImpl.getCourseById(Integer.parseInt(id));
        for (Course course:courseList){
            userInfos = getStu_infoById.GetStu_infoByid(course.getCourse_code());
            for (user_info user_info1 : userInfos) {
                if (user_info1 != null) {
                    scores = getScoreByIdAndCode.getSocreByIdAndCode(user_info1.getId(),course.getCourse_code());
                    if (scores.size() == 0) {
                        Tea_ScoreVO scoreVO = new Tea_ScoreVO(user_info1.getId(), user_info1.getName(), course.getCourse_code(), course.getCourse_name());
                        scoreVOS.add(scoreVO);
                    }
                }
            }
        }
        return LayerResult.getJson(scoreVOS);
    }

    //老师更改成绩使用的学生成绩信息接口
    @RequestMapping("/getStu_Score1")
    public JSON getStu_Score1(HttpServletRequest servletRequest){
        LoggerFactory.getLogger(GetStudentScore.class).info("老师课程到达！！");
        String token = servletRequest.getHeader("AccessToken");
        System.out.println("获取到token:"+token+"  id:"+ JwtUtil.getId(token));
        String id = JwtUtil.getId(token);
        List<Course> courseList ;
        List<Score> scores ;
        List<user_info> userInfos ;
        List<Tea_ScoreVO> scoreVOS = new ArrayList<>();
        courseList = getCourseByIdImpl.getCourseById(Integer.parseInt(id));
        for (Course course:courseList){
            userInfos = getStu_infoById.GetStu_infoByid(course.getCourse_code());
            for (user_info user_info1 : userInfos) {
                if (user_info1 != null) {
                    LoggerFactory.getLogger(GetStudentScore.class).info(String.valueOf(user_info1.getId()));
                    scores = getScoreByIdAndCode.getSocreByIdAndCode(user_info1.getId(),course.getCourse_code());
                    if (scores.size() > 0) {
                        for (Score score : scores) {
                                Tea_ScoreVO scoreVO = new Tea_ScoreVO(user_info1.getId(), user_info1.getName(), course.getCourse_code(), course.getCourse_name(), score.getScore());
                                scoreVOS.add(scoreVO);
                        }
                    }
                }
            }
        }
        return LayerResult.getJson(scoreVOS);
    }
}
