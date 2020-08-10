package com.xck.demo.service.Shiro_Service.Shiro_ServiceImpl;

import com.xck.demo.mapper.Info_ServiceMapper;
import com.xck.demo.model.Permission;
import com.xck.demo.model.Roles;
import com.xck.demo.service.Shiro_Service.GetPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 谢朝康
 * @date 2020/4/23 15:11
 */
@Service
public class GetPermissionServiceImpl implements GetPermissionService {

    @Autowired
    Info_ServiceMapper info_serviceMapper;

    /**
     * 根据用户id获取角色所拥有权限
     * @param id
     * @return Set<String>
     * */
    @Override
    public Set<String> listpermissionById(@NotNull int id) {
        Set<String> result = new HashSet<>();
        List<Roles> list = info_serviceMapper.sel_roles(id);
        List<Permission> permissionList = new ArrayList<>();
        for (Roles roles1 : list){
            int r_id = roles1.getId();
            permissionList = info_serviceMapper.sel_permission(r_id);
        }
        for (Permission permission : permissionList) {
            if (null != permission)
            result.add(permission.getName());
        }
        return result;
    }
}
