package com.xck.demo.controller.teacherhandler;

import com.alibaba.fastjson.JSON;
import com.xck.demo.model.Course;
import com.xck.demo.model.Score;
import com.xck.demo.model.user_info;
import com.xck.demo.service.Tea_Service.Tea_ServiceImpl.GetCourseByIdImpl;
import com.xck.demo.service.Tea_Service.Tea_ServiceImpl.GetScoreByIdAndCodeImpl;
import com.xck.demo.service.Tea_Service.Tea_ServiceImpl.GetStuInfoByIdImpl;
import com.xck.demo.shiro.util.JwtUtil;
import com.xck.demo.util.LayerResult;
import com.xck.demo.vo.Tea_ScoreVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/28 11:30
 */
@Api(tags = "老师查看学生成绩")
@RestController
public class GetStudentScore {

    @Autowired
    GetCourseByIdImpl getCourseByIdImpl;

    @Autowired
    GetScoreByIdAndCodeImpl getScoreByIdAndCode;

    @Autowired
    GetStuInfoByIdImpl getStuInfoById;

    @ApiOperation("老师发布成绩使用的学生成绩信息接口，需要把token放在请求头")
    @RequestMapping("/getStu_Score")
    public JSON getStuScore(HttpServletRequest servletRequest){
        String token = servletRequest.getHeader("AccessToken");
        System.out.println("获取到token:"+token+"  id:"+ JwtUtil.getId(token));
        String id = JwtUtil.getId(token);
        List<Course> courseList ;
        List<user_info> userInfos ;
        List<Score> scores;
        List<Tea_ScoreVO> scoreVos = new ArrayList<>();
        assert id != null;
        courseList = getCourseByIdImpl.getCourseById(Integer.parseInt(id));
        for (Course course:courseList){
            userInfos = getStuInfoById.GetStu_infoByid(course.getCourse_code());
            for (user_info userInfo1 : userInfos) {
                if (userInfo1 != null) {
                    scores = getScoreByIdAndCode.getSocreByIdAndCode(userInfo1.getId(),course.getCourse_code());
                    if (scores.size() == 0) {
                        Tea_ScoreVO scoreVO = new Tea_ScoreVO(userInfo1.getId(), userInfo1.getName(), course.getCourse_code(), course.getCourse_name());
                        scoreVos.add(scoreVO);
                    }
                }
            }
        }
        return LayerResult.getJson(scoreVos);
    }

    @ApiOperation("老师更改学生成绩使用的学生成绩信息接口，需要把token放在请求头")
    @RequestMapping("/getStu_Score1")
    public JSON getStuScore1(HttpServletRequest servletRequest){
        LoggerFactory.getLogger(GetStudentScore.class).info("老师课程到达！！");
        String token = servletRequest.getHeader("AccessToken");
        System.out.println("获取到token:"+token+"  id:"+ JwtUtil.getId(token));
        String id = JwtUtil.getId(token);
        List<Course> courseList ;
        List<Score> scores ;
        List<user_info> userInfos ;
        List<Tea_ScoreVO> scoreVos = new ArrayList<>();
        assert id != null;
        courseList = getCourseByIdImpl.getCourseById(Integer.parseInt(id));
        for (Course course:courseList){
            userInfos = getStuInfoById.GetStu_infoByid(course.getCourse_code());
            for (user_info userInfo1 : userInfos) {
                if (userInfo1 != null) {
                    LoggerFactory.getLogger(GetStudentScore.class).info(String.valueOf(userInfo1.getId()));
                    scores = getScoreByIdAndCode.getSocreByIdAndCode(userInfo1.getId(),course.getCourse_code());
                    if (scores.size() > 0) {
                        for (Score score : scores) {
                                Tea_ScoreVO scoreVO = new Tea_ScoreVO(userInfo1.getId(), userInfo1.getName(), course.getCourse_code(), course.getCourse_name(), score.getScore());
                                scoreVos.add(scoreVO);
                        }
                    }
                }
            }
        }
        return LayerResult.getJson(scoreVos);
    }
}
