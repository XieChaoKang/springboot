package com.xck.demo.Controller.Info_mainHandler;

import com.alibaba.fastjson.JSON;
import com.xck.demo.Model.user_info;
import com.xck.demo.Service.Info_mainService.Info_mainServiceImpl.Sel_InfoServiceImpl;
import com.xck.demo.Util.LayerResult;
import com.xck.demo.VO.InfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 谢朝康
 * @date 2020/4/9 22:20
 * 个人信息数据接口
 */
@RestController
public class Sel_Info {

    @Autowired
    Sel_InfoServiceImpl sel_infoService;

    @RequestMapping("/sel_info")
    public JSON Select_Info(HttpServletRequest request){
        user_info user_info = (user_info) request.getSession().getAttribute("student");
        Logger logger = LoggerFactory.getLogger(Sel_Info.class);
        logger.info(String.valueOf(user_info.getId()));
        user_info user_info1 = sel_infoService.sel(user_info.getId());
        InfoVO infoVO = new InfoVO(user_info1.getId(),user_info1.getName(),user_info1.getGender(),user_info1.getBirthday(),user_info1.getNationality(),user_info1.getID_card(),user_info1.getPolitical_status(),user_info1.getAddress(),user_info1.getPhone(),user_info1.getDepartment(),user_info1.getMajor_name(),user_info1.getAdministrative_class());
        List<InfoVO> list = new ArrayList<>();
        list.add(infoVO);
        return LayerResult.getJson(list);
    }

}
