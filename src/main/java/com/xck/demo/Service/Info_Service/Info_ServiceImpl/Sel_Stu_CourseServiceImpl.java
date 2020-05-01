package com.xck.demo.Service.Info_Service.Info_ServiceImpl;

import com.xck.demo.Mapper.Info_ServiceMapper;
import com.xck.demo.Model.Course;
import com.xck.demo.Service.Info_Service.Sel_Stu_CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/23 17:46
 */
@Service
public class Sel_Stu_CourseServiceImpl implements Sel_Stu_CourseService {

    @Autowired
    Info_ServiceMapper info_serviceMapper;

    @Override
    public List<Course> sel_course(int id) {
        return info_serviceMapper.sel_course(id);
    }
}
