package com.bootdo.modules.flowable.db;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flowable.bpmn.model.ext.ExtChildNode;
import org.flowable.bpmn.model.ext.ExtModelEditor;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import com.bootdo.modules.flowable.domain.ExtDatasourceDO;

@Component
public class SqlNode implements JavaDelegate{
    
	private static Logger logger = LogManager.getLogger(SqlNode.class.getName());
	
	@Override
	public void execute(DelegateExecution execution) {
		// TODO Auto-generated method stub
		Map<String, Object> map = execution.getVariables();
	
		ExtModelEditor editorModel = (ExtModelEditor)map.get("model");
		
		String sql = getSql(editorModel,execution.getCurrentActivityId());
		
		DBFactory dbf = new DBFactory();
    	
		try {
			List result = dbf.execSql((ExtDatasourceDO)map.get(execution.getCurrentActivityId()+"-ds"), sql, map);
			execution.setVariable(execution.getCurrentActivityId()+"ResponseStatusCode", "200");
			String resultStr = "";
			if(result.size()==1)
			{
				resultStr = result.get(0).toString();
				if(resultStr.indexOf("=")>0) {
					resultStr = resultStr.substring(resultStr.indexOf("=")+1, resultStr.length()-1);
				}
				execution.setVariable(execution.getCurrentActivityId()+"SelectResult", resultStr);
			}else
			{
				execution.setVariable(execution.getCurrentActivityId()+"SelectResult", result);
			}
			logger.debug(execution.getCurrentActivityId()+"SelectResult", result);
			logger.debug(execution.getCurrentActivityId()+"ResponseStatusCode", "200");
		} catch (Exception e) {
			execution.setVariable(execution.getCurrentActivityId()+"ResponseStatusCode", "500");
			execution.setVariable(execution.getCurrentActivityId()+"ResponseReason", e.getMessage());
			logger.error(execution.getCurrentActivityId()+"ResponseStatusCode", "500");
			logger.error(execution.getCurrentActivityId()+"ResponseReason", e.getMessage());
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
		logger.debug(activiId+" sql="+sql);
		return sql;
	}
	
}
