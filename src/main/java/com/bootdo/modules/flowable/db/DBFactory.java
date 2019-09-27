package com.bootdo.modules.flowable.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

import com.bootdo.modules.flowable.domain.ExtDatasourceDO;
import com.bootdo.modules.flowable.utils.VarReplaceUtil;

public class DBFactory {
	
	public Connection getConnection(ExtDatasourceDO ds)throws Exception{
		Connection conn = null;
	    Class.forName(ds.getDriverclassname()).newInstance(); //MYSQL驱动
		conn = DriverManager.getConnection(ds.getUrl(), ds.getUsername(), ds.getUserpwd()); //链接本地MYSQL
		return conn;
	}
	
	public boolean execSql(ExtDatasourceDO ds,String sql,Map<String,Object> param)throws Exception
	{
		boolean bl = false;

		Connection conn = getConnection(ds);
		sql = VarReplaceUtil.replace_var(sql,param);
		Statement st = conn.createStatement();
		st.execute(sql);
		bl = true;

		return bl;
	}

}
