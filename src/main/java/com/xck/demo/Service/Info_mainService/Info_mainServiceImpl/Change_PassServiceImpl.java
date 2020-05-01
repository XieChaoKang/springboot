package com.xck.demo.Service.Info_mainService.Info_mainServiceImpl;

import com.xck.demo.Mapper.Info_mainMapper;
import com.xck.demo.Model.user_info;
import com.xck.demo.Service.Info_mainService.Change_PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谢朝康
 * @date 2020/4/12 1:17
 */
@Service
public class Change_PassServiceImpl implements Change_PassService {

    @Autowired
    Info_mainMapper info_mainMapper;

    /**
    * 根据用户id和密码更改用户密码
     * return 0或1以上
     * @param user_info(id,password)
    */
    @Override
    public int Up_Pass(user_info user_info) {
        return info_mainMapper.Up_Pass(user_info);
    }
}
