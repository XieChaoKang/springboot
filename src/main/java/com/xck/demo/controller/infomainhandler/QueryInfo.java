package com.xck.demo.controller.infomainhandler;

import com.alibaba.fastjson.JSON;
import com.xck.demo.model.user_info;
import com.xck.demo.service.Info_mainService.Info_mainServiceImpl.QueryInfoServiceImpl;
import com.xck.demo.shiro.util.JwtUtil;
import com.xck.demo.util.LayerResult;
import com.xck.demo.vo.InfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2020/4/9 22:20
 * 个人信息数据接口
 */
@Api("个人信息数据接口")
@RestController
public class QueryInfo {

    @Autowired
    QueryInfoServiceImpl queryInfoService;

    @ApiOperation("查看个人信息，需要把token放在请求头")
    @RequestMapping("/sel_info")
    public JSON Select_Info(HttpServletRequest servletRequest){
        String token = servletRequest.getHeader("AccessToken");
        System.out.println("获取到token:"+token+"  id:"+JwtUtil.getId(token));
        String id = JwtUtil.getId(token);
        assert id != null;
        user_info userInfo1 = queryInfoService.sel(Integer.parseInt(id));
        InfoVO infoVO = new InfoVO(userInfo1.getId(),userInfo1.getName(),userInfo1.getGender(),userInfo1.getBirthday(),userInfo1.getNationality(),userInfo1.getID_card(),userInfo1.getPolitical_status(),userInfo1.getAddress(),userInfo1.getPhone(),userInfo1.getDepartment(),userInfo1.getMajor_name(),userInfo1.getAdministrative_class());
        List<InfoVO> list = new ArrayList<>();
        list.add(infoVO);
        return LayerResult.getJson(list);
    }

}
