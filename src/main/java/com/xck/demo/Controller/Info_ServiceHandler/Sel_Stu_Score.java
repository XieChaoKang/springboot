package com.xck.demo.Controller.Info_ServiceHandler;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.xck.demo.Model.Score;
import com.xck.demo.Model.user_info;
import com.xck.demo.Service.Info_Service.Info_ServiceImpl.Sel_ScoreServiceImpl;
import com.xck.demo.Shiro.util.JwtUtil;
import com.xck.demo.Util.LayerResult;
import com.xck.demo.Util.ScoreResult;
import com.xck.demo.VO.Stu_ScoreVO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/12 2:20
 * 成绩信息接口
 */
@RestController
public class Sel_Stu_Score {

    @Autowired
    Sel_ScoreServiceImpl sel_scoreService;

    //及格成绩
    //@ResponseBody
    @RequestMapping("/sel_score")
    public JSON Stu_Score(HttpServletRequest servletRequest){
        LoggerFactory.getLogger(Sel_Stu_Course.class).info("成绩到达！！");
        String token = servletRequest.getHeader("AccessToken");
        System.out.println("获取到token:"+token+"  id:"+ JwtUtil.getId(token));
        String id = JwtUtil.getId(token);
        if (id != null) {
            List<Score> stuCourseList = sel_scoreService.sel_score(Integer.parseInt(id));
            List<Stu_ScoreVO> scores = ScoreResult.Score_Result(stuCourseList);
            return LayerResult.getJson(scores);
        }
        else return LayerResult.getJson(new ArrayList());
    }

    //不及格的成绩
    @RequestMapping("/sel_score1")
    public JSON Stu_Score1(HttpServletRequest servletRequest){
        LoggerFactory.getLogger(Sel_Stu_Course.class).info("课程到达！！");
        String token = servletRequest.getHeader("AccessToken");
        System.out.println("获取到token:"+token+"  id:"+ JwtUtil.getId(token));
        String id = JwtUtil.getId(token);
        if (id != null) {
            List<Score> stuCourseList = sel_scoreService.sel_score(Integer.parseInt(id));
            List<Stu_ScoreVO> scores = ScoreResult.Score_Result1(stuCourseList);
            return LayerResult.getJson(scores);
        }
        else return LayerResult.getJson(new ArrayList());
    }


}
