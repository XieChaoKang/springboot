package com.xck.demo.Util;

/*
layui工具类
包装List数据转换为前端Layui能识别的JSON数据
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LayerResult {

    public static JSON getJson(List list){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","成功获取数据！");
        map.put("count",list.size());
        map.put("data",list);

        JSON json = (JSON) JSONObject.toJSON(map);

        return json;
    }

}
