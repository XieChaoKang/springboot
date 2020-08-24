package com.xck.demo.controller.infoservicehandler;

import com.alibaba.fastjson.JSON;
import com.xck.demo.model.Score;
import com.xck.demo.service.Info_Service.Info_ServiceImpl.ScoreServiceImpl;
import com.xck.demo.shiro.util.JwtUtil;
import com.xck.demo.util.LayerResult;
import com.xck.demo.util.ScoreResult;
import com.xck.demo.vo.Stu_ScoreVO;
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
 * @date 2020/4/12 2:20
 * 成绩信息接口
 */
@Api("学生查看成绩")
@RestController
public class StudentScore {

    @Autowired
    ScoreServiceImpl scoreService;

    // 及格成绩

    @ApiOperation("及格成绩接口，需要把token放在请求头")
    @RequestMapping("/sel_score")
    public JSON stuScore(HttpServletRequest servletRequest){
        LoggerFactory.getLogger(StudentCourse.class).info("成绩到达！！");
        String token = servletRequest.getHeader("AccessToken");
        System.out.println("获取到token:"+token+"  id:"+ JwtUtil.getId(token));
        String id = JwtUtil.getId(token);
        if (id != null) {
            List<Score> stuCourseList = scoreService.sel_score(Integer.parseInt(id));
            List<Stu_ScoreVO> scores = ScoreResult.Score_Result(stuCourseList);
            return LayerResult.getJson(scores);
        }
        else {
            return LayerResult.getJson(new ArrayList());
        }
    }

    //不及格的成绩

    @ApiOperation("不及格成绩接口，需要把token放在请求头")
    @RequestMapping("/sel_score1")
    public JSON stuScore1(HttpServletRequest servletRequest){
        LoggerFactory.getLogger(StudentCourse.class).info("课程到达！！");
        String token = servletRequest.getHeader("AccessToken");
        System.out.println("获取到token:"+token+"  id:"+ JwtUtil.getId(token));
        String id = JwtUtil.getId(token);
        if (id != null) {
            List<Score> stuCourseList = scoreService.sel_score(Integer.parseInt(id));
            List<Stu_ScoreVO> scores = ScoreResult.Score_Result1(stuCourseList);
            return LayerResult.getJson(scores);
        }
        else {
            return LayerResult.getJson(new ArrayList());
        }
    }


}
