package com.xck.demo.service.Info_Service.Info_ServiceImpl;

import com.xck.demo.mapper.Info_ServiceMapper;
import com.xck.demo.model.Score;
import com.xck.demo.service.Info_Service.ScoreByIdAndCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谢朝康
 * @date 2020/5/10 11:02
 */
@Service
public class ScoreByIdAndCodeImpl implements ScoreByIdAndCode {

    @Autowired
    Info_ServiceMapper infoServiceMapper1;

    @Override
    public Score getScore(int id, int code) {
        return infoServiceMapper1.selScoreByidAndCode(id,code);
    }
}
