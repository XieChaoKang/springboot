package com.xck.demo.Service.Sel_CourseService.Sel_CourseServiceImpl;

import com.xck.demo.Mapper.Select_CourseMapper;
import com.xck.demo.Model.Elective_courses;
import com.xck.demo.Service.Sel_CourseService.Sel_CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/16 15:36
 */
@Service
public class Sel_CourseServiceImpl implements Sel_CourseService {

    @Autowired
    Select_CourseMapper select_courseMapper;

    /**
     * 批量将学生所选择课程信息更新到数据库中
     * @param Course_if//课程类型 stu_id//用户id
     * @return Elective_courses的list集合
     * */
    @Override
    public List<Elective_courses> sel_ele(@NotNull String Course_if, @NotNull int stu_id) {
        return select_courseMapper.sel_ele(Course_if,stu_id);
    }
}
