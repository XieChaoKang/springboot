package com.xck.demo.Service.Info_Service;

import com.xck.demo.Model.Score;

/**
 * @author 谢朝康
 * @date 2020/5/10 11:01
 */
public interface ScoreByIdAndCode {
    Score getScore(int id,int code);
}
