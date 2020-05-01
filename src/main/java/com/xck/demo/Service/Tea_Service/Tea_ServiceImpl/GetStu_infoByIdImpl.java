package com.xck.demo.Service.Tea_Service.Tea_ServiceImpl;

import com.xck.demo.Mapper.TeacherMapper;
import com.xck.demo.Model.user_info;
import com.xck.demo.Service.Tea_Service.GetStu_infoById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/28 10:55
 */
@Service
public class GetStu_infoByIdImpl implements GetStu_infoById {

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
