package com.xck.demo.service.Tea_Service.Tea_ServiceImpl;

import com.xck.demo.mapper.TeacherMapper;
import com.xck.demo.model.user_info;
import com.xck.demo.service.Tea_Service.GetStu_infoById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/28 10:55
 */
@Service
public class GetStuInfoByIdImpl implements GetStu_infoById {

    @Autowired
    TeacherMapper teacherMapper1;

    /**
     * 老师根据任教的课程id获取选择该课程的学生信息
     * @param id //课程id
     * @return List<user_info>
     * @exception NullPointerException
     * */
    @Override
    public List<user_info> GetStu_infoByid(int id) {
        return teacherMapper1.getInfoByCode(id);
    }
}
