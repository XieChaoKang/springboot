package com.xck.demo.service.Tea_Service;

import com.xck.demo.model.Course;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/28 10:56
 */
public interface GetCourseById {
    List<Course> getCourseById(int tea_id);
}
