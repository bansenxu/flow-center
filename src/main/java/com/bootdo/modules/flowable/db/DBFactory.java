package com.bootdo.modules.flowable.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

import com.bootdo.modules.flowable.domain.ExtDatasourceDO;
import com.bootdo.modules.flowable.utils.VarReplaceUtil;

public class DBFactory {
	
	public Connection getConnection(ExtDatasourceDO ds){
		Connection conn = null;
		try{
			 Class.forName(ds.getDriverclassname()).newInstance(); //MYSQL驱动
		     conn = DriverManager.getConnection(ds.getUrl(), ds.getUsername(), ds.getUserpwd()); //链接本地MYSQL
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	
	public boolean execSql(ExtDatasourceDO ds,String sql,Map<String,Object> param)
	{
		boolean bl = false;
		try{
			Connection conn = getConnection(ds);
			sql = VarReplaceUtil.replace_var(sql,param);
			Statement st = conn.createStatement();
			st.execute(sql);
			bl = true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return bl;
	}

}
