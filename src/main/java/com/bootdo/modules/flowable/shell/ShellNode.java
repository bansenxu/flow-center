package com.bootdo.modules.flowable.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flowable.bpmn.model.ext.ExtChildNode;
import org.flowable.bpmn.model.ext.ExtModelEditor;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import com.bootdo.modules.flowable.domain.ExtDatasourceDO;
import com.bootdo.modules.flowable.rest.FlowableRest;
import com.bootdo.modules.flowable.utils.VarReplaceUtil;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;


@Component
public class ShellNode implements JavaDelegate{
	
	private static String DEFAULTCHART = "UTF-8";
	
	private static final int TIME_OUT = 1000 * 5 * 60;
	
	private static Logger logger = LogManager.getLogger(ShellNode.class.getName());
	
	@Override
	public void execute(DelegateExecution execution) {
		
		// TODO Auto-generated method stub
		Map<String, Object> map = execution.getVariables();
		String result = "";
		
		ExtModelEditor editorModel = (ExtModelEditor)map.get("model");
		
		String[] commond = getCommond(editorModel,execution.getCurrentActivityId());
		if(commond!=null)
		{
			for(int i=0;i<commond.length;i++){
				commond[i] = VarReplaceUtil.replace_var(commond[i],execution.getVariables());
				logger.debug("shell node will be excute commond:"+commond[i]);
			}
		}
		
		ExtDatasourceDO ds = (ExtDatasourceDO)map.get(execution.getCurrentActivityId()+"-ds");
		try {
			result = executeCommond(ds,commond);
			execution.setVariable(execution.getCurrentActivityId()+"ResponseStatusCode", "200");
			execution.setVariable(execution.getCurrentActivityId()+"ShellNodeResult", result);
			logger.debug(execution.getCurrentActivityId()+"ResponseStatusCode", "200");
			logger.debug(execution.getCurrentActivityId()+"ShellNodeResult", result);
		} catch (Exception e) {
			execution.setVariable(execution.getCurrentActivityId()+"ResponseStatusCode", "500");
			execution.setVariable(execution.getCurrentActivityId()+"ResponseReason", e.getMessage());
			logger.error(execution.getCurrentActivityId()+"ResponseStatusCode", "500");
			logger.error(execution.getCurrentActivityId()+"ResponseReason", e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public String executeCommond(ExtDatasourceDO ds,String[] commonds)throws Exception
	{
		String result = "";
		if(ds==null){
			throw new Exception("目标服务器未获取成功");
		}
		
		/*构建目标服务器连接*/
		String ip = ds.getUrl();
		String username = ds.getUsername();
		String password = ds.getUserpwd();
		String cmd = "uname -a";
		Connection connection = login(ip, username, password);
		for(String command:commonds)
		{
			result = execmd(connection, command);
		}
		connection.close();
		
		return result;
	}
	
	public static void main(String[] ss)throws Exception
	{
		String ip = "122.112.4.152";
		String username = "root";
		String password = "www.it663.com";
		Connection connection = login(ip, username, password);
		//execmd(connection, "ps -ef|grep java");
		execmd(connection, "mkdir /home/testshell2");
		connection.close();
	}
	
	/* 从json中获取 shell命令 */
	public String[] getCommond(ExtModelEditor editorModel,String activiId) {
		String sql = null;
		for (ExtChildNode tem : editorModel.getChildShapes()) {
			if (tem != null && tem.getProperties().getOverrideid().equals(activiId)) {
				if (tem.getProperties() != null) {
					if (tem.getProperties().getServicetaskexpression() != null) {
						sql = tem.getProperties().getServicetaskexpression();
					}
				}
			}
		}
		String[] commonds = sql.split("\n");
		return commonds;
	}

	private static Connection login(String ip, String username, String password) {
		boolean flag = false;
		Connection connection = null;
		try {
			connection = new Connection(ip);
			connection.connect();// 连接
			flag = connection.authenticateWithPassword(username, password);// 认证
			if (flag) {
				logger.info("================登录成功==================");
				return connection;
			}
		} catch (IOException e) {
			logger.error("=========登录失败=========" + e);
			connection.close();
		}
		return connection;
	}

	/**
	 * * 远程执行shll脚本或者命令 *  
	 * * @param cmd * 即将执行的命令 
	 * * @return 命令执行完后返回的结果值
	 */

	private static String execmd(Connection connection, String cmd)throws Exception {
		String result = "";
		InputStream stdOut = null;
		InputStream stdErr = null;
		String outStr = "";
		String outErr = "";
		int ret = -1;
		try {
			if (connection != null) {
//				Session session = connection.openSession();// 打开一个会话
//				
//				session.execCommand(cmd);// 执行命令
//				result = processStdout(session.getStdout(), DEFAULTCHART);
//				System.out.println(result);
				
				// 如果为得到标准输出为空，说明脚本执行出错了
				/*
				 * if (StringUtils.isBlank(result)) {
				 * System.out.println("得到标准输出为空,链接conn:" + connection +
				 * ",执行的命令：" + cmd); result = processStdout(session.getStderr(),
				 * DEFAULTCHART); } else { System.out.println("执行命令成功,链接conn:" +
				 * connection + ",执行的命令：" + cmd); }
				 */
				Session session = connection.openSession();
				session.execCommand(cmd);
				stdOut = new StreamGobbler(session.getStdout());
				outStr = processStream(stdOut, DEFAULTCHART);
//				LOG.info("caijl:[INFO] outStr=" + outStr);
				result = outStr;
				logger.info("shell command out:"+outStr);
				stdErr = new StreamGobbler(session.getStderr());
				outErr = processStream(stdErr, DEFAULTCHART);
//				LOG.info("caijl:[INFO] outErr=" + outErr);
				logger.info("shell command error:"+outErr);
				session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
				ret = session.getExitStatus();
				session.close();
			}
		} catch (IOException e) {
			logger.error("执行命令失败,链接conn:" + connection + ",执行的命令：" + cmd + "  " + e);
			throw e;
		}
		return result;

	}
	
	private static String processStream(InputStream in, String charset) throws IOException {
		 byte[] buf = new byte[1024];
		 StringBuilder sb = new StringBuilder();
		 while (in.read(buf) != -1) {
		 sb.append(new String(buf, charset));
		 }
		 return sb.toString();
	}

	/**
	 * * 解析脚本执行返回的结果集 *  
	 * * @param in *            
	 * 	输入流对象 * @param charset *      
	 *  编码 * @return 以纯文本的格式返回
	 * 
	 */

	private static String processStdout(InputStream in, String charset) {
		InputStream stdout = new StreamGobbler(in);
		StringBuffer buffer = new StringBuffer();
		;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset));
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\n");
				System.out.println(line);
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			logger.error("解析脚本出错：" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("解析脚本出错：" + e.getMessage());
			e.printStackTrace();
		}
		return buffer.toString();
	}

}
