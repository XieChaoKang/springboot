package com.xck.demo.service.Sel_CourseService.Sel_CourseServiceImpl;

import com.xck.demo.Mapper.Select_CourseMapper;
import com.xck.demo.service.Sel_CourseService.Get_CapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谢朝康
 * @date 2020/5/9 9:32
 */
@Service
public class GetCapacityServiceImpl implements Get_CapacityService {

    @Autowired
    Select_CourseMapper select_courseMapper;

    /**
     * 根据课程id获取课程容量
     * @param code
     * @return integer
     */
    @Override
    public Integer getCourse_capacity(int code) {
        return select_courseMapper.getCourse_capacity(code);
    }
}
