package com.xck.demo.Service.Info_Service.Info_ServiceImpl;

import com.xck.demo.Mapper.Info_ServiceMapper;
import com.xck.demo.Model.Score;
import com.xck.demo.Model.Stu_course;
import com.xck.demo.Service.Info_Service.Sel_ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/12 2:08
 */
@Service
public class Sel_ScoreServiceImpl implements Sel_ScoreService {

    @Autowired
    Info_ServiceMapper Mapper;

    /**
     * 根据用户id连表查询获取用户成绩信息
     * @param id 用户id
     * @return List集合
     * 因连表查询，所以可能产生部分重复或多余信息
     * */
    @Override
    public List<Score> sel_score(@NotNull int id) {
        return Mapper.sel_score(id);
    }
}
