package com.xck.demo.service.Info_Service;

import com.xck.demo.model.Stu_course;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/10 23:48
 */
public interface Sel_TestService {
    List<Stu_course> sel_test(int id);
    String sel_name(int id);
}
