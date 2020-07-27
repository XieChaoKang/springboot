package com.xck.demo.service.Tea_Service;

import com.xck.demo.model.Score;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/28 11:27
 */
public interface GetScoreByIdAndCode {
    List<Score> getSocreByIdAndCode(int stu_id,int Course_code);
}
