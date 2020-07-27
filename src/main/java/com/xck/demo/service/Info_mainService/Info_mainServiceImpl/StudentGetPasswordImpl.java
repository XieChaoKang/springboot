package com.xck.demo.service.Info_mainService.Info_mainServiceImpl;

import com.xck.demo.Mapper.Info_mainMapper;
import com.xck.demo.model.user_info;
import com.xck.demo.service.Info_mainService.Stu_getPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @author 谢朝康
 * @date 2020/4/21 0:17
 */
@Service
public class StudentGetPasswordImpl implements Stu_getPassword {

    @Autowired
    Info_mainMapper infoMainMapper1;

    /**
     * 根据用户id获取用户密码
     * @param id 用户id
     * @return 用户实体
     * */
    @Override
    public  user_info getPassword(@NotNull int id) {
        return infoMainMapper1.getPassword(id);
    }

    @Override
    public user_info getUserInfoByIdAndPassword(int id, String password) {
        return infoMainMapper1.getUserInfoByIdAndPassword(id,password);
    }
}
