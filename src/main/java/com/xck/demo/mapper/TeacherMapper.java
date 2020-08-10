package com.xck.demo.mapper;

import com.xck.demo.model.Course;
import com.xck.demo.model.Score;
import com.xck.demo.model.user_info;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/28 10:58
 */
@Repository
@Mapper
public interface TeacherMapper {

    /**根据老师id获取课程信息*/
    @Select("select course.course_code,course.course_name from course left join tea_course on tea_course.course_code = course.course_code where tea_course.tea_id")
    List<Course> getCourseById(int tea_id);

    /**老师根据任教的课程id获取选择该课程的学生信息*/
    @Select("select user_info.id,user_info.name from user_info left join stu_course on stu_course.stu_id = user_info.id where stu_course.course_code = #{id}")
    List<user_info> getInfoByCode(int id);

    @Select("select score.score from score where stu_id = #{stu_id} and Course_code = #{Course_code}")
    List<Score> getScoreByIdAndCode(@Param("stu_id") int stu_id,@Param("Course_code") int Course_code);

    @Insert("insert into score values(#{course_code},#{stu_id},#{score})")
    int AddStuScore(Score score);

    @Update("update score set score = #{score} where course_code = #{code}")
    int upStuScore(@Param("code") int course_code,@Param("score") int score);
}
