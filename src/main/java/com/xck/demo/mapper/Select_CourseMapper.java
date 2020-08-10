package com.xck.demo.mapper;

import com.xck.demo.model.Course;
import com.xck.demo.model.Elective_courses;
import com.xck.demo.model.Stu_course;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/16 14:35
 * 网上选课的mapper文件
 */
@Repository
@Mapper
public interface Select_CourseMapper {

   //将学生选择课程信息批量更新到数据库
   @Insert({
           "<script>",
           "insert into stu_course(stu_id,course_code) values ",
           "<foreach collection='list' item='i' index='index' separator=','>",
           "(#{i.stu_id}, #{i.course_code})",
           "</foreach>",
           "</script>"
   })
   int Add_Sel_Course(@Param("list") List<Stu_course> list);

   //查询课程信息以便学生进行选课
    @Select("select * from elective_courses where course_if = #{Course_if} and course_code not in(select course_code from stu_course where stu_id = #{stu_id})")
    @Results({
            @Result(column = "course_code",property = "course_code"),
            @Result(column = "course_if",property = "course_if"),
            @Result(column = "Course_capacity",property = "Course_capacity"),
            @Result(column = "course_code",property = "course",one = @One(select = "com.xck.demo.Mapper.Select_CourseMapper.sel_course",fetchType = FetchType.EAGER))
    })
    List<Elective_courses> sel_ele(@Param("Course_if") String Course_if,@Param("stu_id") int stu_id);

    @Select("select * from course where course_code = #{course_code} and course_code not in(select course_code from stu_course where stu_id = #{stu_id})")
    List<Course> sel_course(int course_code);

    @Select("select Course_capacity from elective_courses where Course_code = #{course_code}")
    Integer getCourse_capacity(int course_code);
//"update Elective_courses set Course_capacity set Course_capacity = #{Course_capacity} where Course_code = #{code}"
    @Update({
            "<script>",
            "<foreach collection='list' item='i' separator=';'>",
            "update elective_courses",
            "set Course_capacity = #{i.Course_capacity} where Course_code = #{i.Course_code}",
            "</foreach>",
            "</script>"
    })
    int UpCourse_capacity(@Param("list") List<Elective_courses> list);
}
