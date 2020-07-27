package com.xck.demo.service.Info_Service.Info_ServiceImpl;

import com.xck.demo.Mapper.Info_ServiceMapper;
import com.xck.demo.model.Stu_course;
import com.xck.demo.service.Info_Service.Sel_TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/10 23:52
 */
@Service
public class TestServiceImpl implements Sel_TestService {

    @Autowired
    Info_ServiceMapper info_serviceMapper;

    /**
     * 根据用户id连表查询获取用户考试信息
     * @param id 用户id
     * @return List集合
     * 因连表查询，所以可能产生部分重复或多余信息
     * */
    @Override
    public List<Stu_course> sel_test(@NotNull int id) {
        return info_serviceMapper.sel_info(id);
    }

    /**
     * 根据用户id查询用户名字
     * @param id
     * @return String
     * */
    public String sel_name(@NotNull int id){
        return info_serviceMapper.sel_name(id).getName();
    }
}
