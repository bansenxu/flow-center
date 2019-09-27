package com.bootdo.modules.flowable.db;

import java.util.Map;

import org.flowable.bpmn.model.ext.ExtChildNode;
import org.flowable.bpmn.model.ext.ExtModelEditor;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import com.bootdo.modules.flowable.domain.ExtDatasourceDO;

@Component
public class SqlNode implements JavaDelegate{
    
	@Override
	public void execute(DelegateExecution execution) {
		// TODO Auto-generated method stub
		Map<String, Object> map = execution.getVariables();
	
		ExtModelEditor editorModel = (ExtModelEditor)map.get("model");
		
		String sql = getSql(editorModel,execution.getCurrentActivityId());
		
		DBFactory dbf = new DBFactory();
    	
		try {
			dbf.execSql((ExtDatasourceDO)map.get(execution.getCurrentActivityId()+"-ds"), sql, map);
			execution.setVariable("ResponseStatusCode", "200");
		} catch (Exception e) {
			execution.setVariable("ResponseStatusCode", "500");
			execution.setVariable("ResponseReason", e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*从json中获取sql*/
	public String getSql(ExtModelEditor editorModel,String activiId)
	{
		String sql = null;
		for(ExtChildNode tem:editorModel.getChildShapes())
		{
			if(tem!=null && tem.getProperties().getOverrideid().equals(activiId)){
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
