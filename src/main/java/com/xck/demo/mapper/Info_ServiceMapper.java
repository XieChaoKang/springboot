package com.xck.demo.mapper;

import com.xck.demo.model.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/10 22:37
 * 信息查询的mapper
 */
@Repository
@Mapper
public interface Info_ServiceMapper {

    @Select("select course.course_name,course.course_teacher,course.course_location,course.course_time from stu_course left join course on course.course_code = stu_course.course_code where stu_course.stu_id = #{id} and ( course.course_class = #{course_class} or course.course_class = 1 ) and stu_course.course_code not in(select course_code from score where stu_id = #{id})")
    @Results({
            @Result(column = "course_name",property = "Course_name"),
            @Result(column = "course_teacher",property = "Course_teacher"),
            @Result(column = "course_location",property = "Course_location"),
            @Result(column = "course_time",property = "Course_time")
    })
    List<Course> sel_course(@Param("id") int id,@Param("course_class") String course_class);

    //连表查询考试信息
    @Select("select * from stu_course where stu_id = #{id} ")
    @Results({
            @Result(column = "stu_id",property = "stu_id"),
            @Result(column = "course_code",property = "course_code"),
            @Result(column = "course_code",property = "test",one = @One(select = "com.xck.demo.Mapper.Info_ServiceMapper.sel_test",fetchType = FetchType.EAGER))
    })
    List<Stu_course> sel_info(int id);

    @Results({
            @Result(column = "course_code",property = "course_code",id = true),
            @Result(column = "Course_name",property = "Course_name"),
            @Result(column = "time",property = "time"),
            @Result(column = "location",property = "location"),
            @Result(column = "Seat_num",property = "Seat_num")
    })
    @Select("select * from test where course_code = #{course_code}")
    List<Test> sel_test(int course_code);

    @Select("select * from score where stu_id = #{id} and Course_code = #{code}")
    Score selScoreByidAndCode(@Param("id") int id,@Param("code") int code);

    //查询姓名
    @Select("select user_info.name from user_info where id = #{id}")
    user_info sel_name(int id);

    //连表查询成绩信息
    @Select("select * from score where stu_id = #{id}")
    @Results({
            @Result(column = "stu_id",property = "stu_id"),
            @Result(column = "course_code",property = "course_code"),
            @Result(column = "score", property = "score"),
            @Result(column = "school_year", property = "school_year"),
            @Result(column = "course_code",property = "course",one = @One(select = "com.xck.demo.Mapper.Info_ServiceMapper.sel1",fetchType = FetchType.EAGER))
    })
    List<Score> sel_score(int id);

    @Select("select course_name,credit from course where course_code = #{course_code}")
    List<Course> sel1(int course_code);

    //连表查询用户角色信息
    @Select("select roles.id,roles.name from user_info left join user_roles on user_info.id = user_roles.u_id left join roles on roles.id = user_roles.r_id where user_info.id = #{id} ")
    List<Roles> sel_roles(int id);

    //连表查询角色权限信息
    @Select("select permission.name from roles left join role_permission on role_permission.r_id = roles.id " +
            "left join permission on permission.id = role_permission.p_id where roles.id = #{id} ")
    List<Permission> sel_permission(int id);
}
