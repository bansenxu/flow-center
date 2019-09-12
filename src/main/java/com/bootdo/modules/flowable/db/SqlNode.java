package com.bootdo.modules.flowable.db;

import java.util.Map;

import javax.sql.DataSource;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootdo.modules.flowable.domain.ExtDatasourceDO;
import com.bootdo.modules.flowable.domain.ext.ExtChildNode;
import com.bootdo.modules.flowable.domain.ext.ExtModelEditor;

@Component
public class SqlNode implements JavaDelegate{
    
	@Override
	public void execute(DelegateExecution execution) {
		// TODO Auto-generated method stub
		Map<String, Object> map = execution.getVariables();

		ExtModelEditor editorModel = (ExtModelEditor)map.get("model");
		
		String sql = getSql(editorModel);
		
		DBFactory dbf = new DBFactory();
    	
		dbf.execSql((ExtDatasourceDO)map.get("dataSource"), sql, map);
	}
	
	/*从json中获取sql*/
	public String getSql(ExtModelEditor editorModel)
	{
		String sql = null;
		for(ExtChildNode tem:editorModel.getChildShapes())
		{
			if(tem!=null){
				if(tem.getProperties()!=null)
				{
					if(tem.getProperties().getServicetaskexpression()!=null)
					{
						sql = tem.getProperties().getServicetaskexpression();
						sql = sql.replace("\n", "");
					}
				}
			}
		}
		return sql;
	}
	
}
