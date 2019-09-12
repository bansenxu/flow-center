package com.bootdo.modules.flowable.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bootdo.modules.flowable.domain.ExtDatasourceDO;
import com.bootdo.modules.flowable.service.ExtDatasourceService;

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
	
	public String build_SQL(String sql,Map<String,Object> param)
	{
		for(String key:param.keySet()){
			try{
				sql = sql.replace("${"+key+"}", (String)param.get(key));
			}catch(Exception e)
			{
				continue;
			}
			
		}
		System.out.println("sql==="+sql);
		return sql;
	}
	
	public boolean execSql(ExtDatasourceDO ds,String sql,Map<String,Object> param)
	{
		boolean bl = false;
		try{
			Connection conn = getConnection(ds);
			sql = build_SQL(sql,param);
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
