package com.xck.demo.service.Info_mainService.Info_mainServiceImpl;

import com.xck.demo.mapper.Info_mainMapper;
import com.xck.demo.model.user_info;
import com.xck.demo.service.Info_mainService.Stu_LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @author 谢朝康
 * @date 2020/4/4 14:56
 */
@Service
public class Stu_LoginServiceImpl implements Stu_LoginService {

    @Autowired
    Info_mainMapper mainMapper;

    /**
     * 根据用户id获取用户信息
     * @param id 用户id
     * @return 用户实体
     * */
    @Override
    public user_info Stu_Login(@NotNull int id) {
        return mainMapper.Stu_Login(id);
    }
}
