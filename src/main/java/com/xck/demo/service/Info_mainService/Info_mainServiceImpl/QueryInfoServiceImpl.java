package com.xck.demo.service.Info_mainService.Info_mainServiceImpl;

import com.xck.demo.Mapper.Info_mainMapper;
import com.xck.demo.model.user_info;
import com.xck.demo.service.Info_mainService.Sel_InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @author 谢朝康
 * @date 2020/4/9 22:44
 */
@Service
public class QueryInfoServiceImpl implements Sel_InfoService {

    @Autowired
    Info_mainMapper info_mainMapper;

    /**
     * 根据用户id获取用户信息
     *@param id 用户id
     * @return 用户实体
     * @exception //UserNotFoundException
     * */
    @Override
    public user_info sel(@NotNull int id) {
        return info_mainMapper.sel_Info(id);
    }
}
