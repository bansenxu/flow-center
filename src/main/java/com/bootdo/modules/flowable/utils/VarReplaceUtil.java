package com.bootdo.modules.flowable.utils;

import java.util.Map;

public class VarReplaceUtil {
	
	public static String replace_var(String str,Map<String,Object> param)
	{
		for(String key:param.keySet()){
			try{
				str = str.replace("${"+key+"}", (String)param.get(key));
			}catch(Exception e)
			{
				continue;
			}
			
		}
		//System.out.println("sql==="+str);
		return str;
	}

}
