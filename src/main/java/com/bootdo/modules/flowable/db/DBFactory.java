package com.bootdo.modules.flowable.db;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.entity.ByteArrayEntity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bootdo.modules.flowable.domain.ExtDatasourceDO;
import com.bootdo.modules.flowable.utils.VarReplaceUtil;

public class DBFactory {
	
	public Connection getConnection(ExtDatasourceDO ds)throws Exception{
		Connection conn = null;
	    Class.forName(ds.getDriverclassname()).newInstance(); //MYSQL驱动
		conn = DriverManager.getConnection(ds.getUrl(), ds.getUsername(), ds.getUserpwd()); //链接本地MYSQL
		return conn;
	}
	
	public List execSql(ExtDatasourceDO ds,String sql,Map<String,Object> param)throws Exception
	{
		Connection conn = getConnection(ds);
		sql = VarReplaceUtil.replace_var(sql,param);
		Statement st = conn.createStatement();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		if(sql.indexOf("select")>-1 && sql.indexOf("insert")<0)
		{
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
	        int colomn = rsmd.getColumnCount();
			Map<String,Object> temp = null; 
			while(rs.next())
			{
				temp = new HashMap<String,Object>();
				for(int i = 1; i <= colomn; i++) {
	        		temp.put(rsmd.getColumnName(i), rs.getObject(i));
	        	}
	        	list.add(temp);
			}
		}else{
			st.execute(sql);
		}
		
		return list;
	}
	
	public static void main(String[] ss)
	{
		Connection conn = null;
	    try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workflow?useUnicode=true&characterEncoding=utf8",
					"root", "123");
			String sql = "select b.BYTES_ from act_ge_bytearray b where b.ID_='edbc8686-f086-11e9-990b-309c23664f9a'";
			Statement st = conn.createStatement();
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			ResultSet rs = st.executeQuery(sql);
			Map<String,Object> temp = null; 
			ByteArrayInputStream msgContent = null;
			while(rs.next())
			{
				msgContent = (ByteArrayInputStream)rs.getBinaryStream(1);
			}
			int size = 0;
		            
		    byte[] byte_data = new byte[msgContent.available()];
		    msgContent.read(byte_data, 0,byte_data.length);

		    ByteArrayInputStream byteInt=new ByteArrayInputStream(byte_data);
		    ObjectInputStream objInt=new ObjectInputStream(byteInt);

		    List<ByteArrayEntity> result = (List<ByteArrayEntity>)objInt.readObject();//byte[]转map
		    String sq = JSON.toJSONString(result);
		    System.err.println(sq);
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
