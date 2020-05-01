package com.xck.demo.Mapper;

import com.xck.demo.Model.user_info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author 谢朝康
 * @date 2020/4/8 0:46
 * 个人信息维护的mapper
 */
@Repository
@Mapper
public interface Info_mainMapper {

    //@Options(useCache = true,keyProperty = "id") 获取自增字段的值
    @Select("SELECT * FROM user_info WHERE id = #{id} ")
    user_info Stu_Login(int id);

    @Select("SELECT password,salt FROM user_info WHERE id = #{id}")
    user_info getPassword(int id);

    //@Select("select * from stu_info")
    //List<user_info> list();

    @Select("select * from user_info where id = #{id}")
    user_info sel_Info(int id);

    @Update("update user_info set password = #{password} where id = #{id}")
    int Up_Pass(user_info user_info);
}
