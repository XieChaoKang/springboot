package com.xck.demo.service.Shiro_Service.Shiro_ServiceImpl;

import com.xck.demo.mapper.Info_ServiceMapper;
import com.xck.demo.model.Roles;
import com.xck.demo.service.Shiro_Service.getRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 谢朝康
 * @date 2020/4/22 17:50
 */
@Service
public class GetRolesServiceImpl implements getRolesService {

    @Autowired
    Info_ServiceMapper serviceMapper;

    /**
     * 根据用户id获取用户拥有角色
     * @param id
     * @return Set<String>
     * */
    @Override
    public Set<String> sel_roles(@NotNull int id){
        List<Roles> list = serviceMapper.sel_roles(id);
        Set<String> result = new HashSet<>();
        for (Roles roles:list){
            result.add(roles.getName());
        }
        return result;
    }
}
