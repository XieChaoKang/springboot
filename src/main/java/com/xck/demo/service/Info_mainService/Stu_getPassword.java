package com.xck.demo.service.Info_mainService;

import com.xck.demo.model.user_info;
import org.apache.ibatis.annotations.Param;

/**
 * @author 谢朝康
 * @date 2020/4/21 0:16
 */
public interface Stu_getPassword {
    user_info getPassword(int id);

    user_info getUserInfoByIdAndPassword(@Param("id") int id, @Param("password") String password);
}
