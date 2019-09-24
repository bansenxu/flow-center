package com.bootdo.modules.flowable.utils;

import java.util.List;

import org.flowable.bpmn.model.ext.ExtChildNode;
import org.flowable.bpmn.model.ext.ExtModelEditor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TransUtil {
	
	public static ExtModelEditor transExtModelEditor(String json)
	{
		//顶级json
		JSONObject jsonObject = (JSONObject) JSON.parse(json);
		
		//childShapes josn
		List<ExtChildNode> childNods = JSON.parseArray(jsonObject.getString("childShapes"), ExtChildNode.class);
		ExtModelEditor result = new ExtModelEditor();
		result.setModelId(jsonObject.getString("modelId"));
		result.setChildShapes(childNods);
		return result;
	}

}
