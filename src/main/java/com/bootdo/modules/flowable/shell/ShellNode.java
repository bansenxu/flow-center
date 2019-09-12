package com.bootdo.modules.flowable.shell;

import java.util.Map;

import javax.sql.DataSource;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootdo.modules.flowable.domain.ExtDatasourceDO;
import com.bootdo.modules.flowable.domain.ext.ExtChildNode;
import com.bootdo.modules.flowable.domain.ext.ExtModelEditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;


@Component
public class ShellNode implements JavaDelegate{
	
	private static String DEFAULTCHART = "UTF-8";
	
	@Override
	public void execute(DelegateExecution execution) {
		

		// TODO Auto-generated method stub
		Map<String, Object> map = execution.getVariables();

		ExtModelEditor editorModel = (ExtModelEditor)map.get("model");
		
		String[] commond = getCommond(editorModel);
		
		ExtDatasourceDO ds = (ExtDatasourceDO)map.get("dataSource");
		try {
			executeCommond(ds,commond);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean executeCommond(ExtDatasourceDO ds,String[] commonds)throws Exception
	{
		boolean bl = false;
		if(ds==null){
			throw new Exception("目标服务器未获取成功");
		}
		
		/*构建目标服务器连接*/
		String ip = ds.getUrl();
		String username = ds.getUsername();
		String password = ds.getUserpwd();
		String cmd = "uname -a";
		Connection connection = login(ip, username, password);
		
		for(String commond:commonds){
			String execmd = execmd(connection, commond);
			System.out.println(execmd);
		}
		bl = true;
		return bl;
	}
	
	/* 从json中获取 shell命令 */
	public String[] getCommond(ExtModelEditor editorModel) {
		String sql = null;
		for (ExtChildNode tem : editorModel.getChildShapes()) {
			if (tem != null) {
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
				System.out.println("================登录成功==================");
				return connection;
			}
		} catch (IOException e) {
			System.out.println("=========登录失败=========" + e);
			connection.close();
		}
		return connection;
	}

	/**
	 * * 远程执行shll脚本或者命令 *  * @param cmd *            即将执行的命令 * @return
	 * 命令执行完后返回的结果值
	 */

	private static String execmd(Connection connection, String cmd) {
		String result = "";
		try {
			if (connection != null) {
				Session session = connection.openSession();// 打开一个会话
				session.execCommand(cmd);// 执行命令
				result = processStdout(session.getStdout(), DEFAULTCHART);
				System.out.println(result);
				// 如果为得到标准输出为空，说明脚本执行出错了
				/*
				 * if (StringUtils.isBlank(result)) {
				 * System.out.println("得到标准输出为空,链接conn:" + connection +
				 * ",执行的命令：" + cmd); result = processStdout(session.getStderr(),
				 * DEFAULTCHART); } else { System.out.println("执行命令成功,链接conn:" +
				 * connection + ",执行的命令：" + cmd); }
				 */
				connection.close();
				session.close();
			}
		} catch (IOException e) {
			System.out.println("执行命令失败,链接conn:" + connection + ",执行的命令：" + cmd + "  " + e);
			e.printStackTrace();
		}
		return result;

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
			System.out.println("解析脚本出错：" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("解析脚本出错：" + e.getMessage());
			e.printStackTrace();
		}
		return buffer.toString();
	}

}
