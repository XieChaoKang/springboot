package com.xck.demo.util;

import com.xck.demo.model.Stu_course;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * @author 谢朝康
 * @date 2020/4/16 15:09
 */
public class Stu_courseDaoProvider {

    public String insertAll(Map map){
        List<Stu_course> stuCourseList = (List<Stu_course>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO stu_course ");
        sb.append("(stu_id, course_code) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].stu_id}), #'{'list[{0}].course_code})");
        for (int i = 0; i < stuCourseList.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < stuCourseList.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
