package com.bootdo.modules.flowable.utils;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.modules.common.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Json2Map {

    public static Map<String,Object> getMap(String json){
        if(StringUtils.isEmpty(json)){
            return null;
        }else {
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap = (Map<String, Object>) JSONObject.parse(json);
            return returnMap;
        }
    }

    public static String getJsonStr(Map map){
        if(map!=null){
            return JSONObject.toJSONString(map);
        }else {
            return null;
        }
    }
}
