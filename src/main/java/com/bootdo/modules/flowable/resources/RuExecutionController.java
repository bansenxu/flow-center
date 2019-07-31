package com.bootdo.modules.flowable.resources;

import java.util.List;
import java.util.Map;

import com.bootdo.modules.flowable.domain.RuExecutionDO;
import com.bootdo.modules.flowable.service.RuExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-10 19:40:02
 */
 
@Controller
@RequestMapping("/flowable/ruExecution")
public class RuExecutionController extends BaseController {
	@Autowired
	private RuExecutionService ruExecutionService;
	
	@GetMapping()
//	@RequiresPermissions("flowable:ruExecution:ruExecution")
	String RuExecution(){
		String code = this.getRequest().getParameter("code");
		this.getRequest().setAttribute("code",code);
		return "flowable/ruExecution/ruExecution";
	}

	@GetMapping("/task")
//	@RequiresPermissions("flowable:ruExecution:ruExecution")
	String RuExecutionTask(){
		String code = this.getRequest().getParameter("code");
		this.getRequest().setAttribute("code",code);
		return "flowable/ruExecution/ruExecutionTask";
	}

	/**
	 * 当前登录人的代办任务
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/taskList")
	public PageUtils taskList(@RequestParam Map<String, Object> params){
		String username = getUsername();
		logger.info("username:{}",username);
		//默认用户传入a
		params.put("userId",username);
		//查询列表数据
		Query query = new Query(params);
		List<Map<String, Object>> ruExecutionList = ruExecutionService.taskList(query);
		int total = ruExecutionService.taskCount(query);
		PageUtils pageUtils = new PageUtils(ruExecutionList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("flowable:ruExecution:ruExecution")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RuExecutionDO> ruExecutionList = ruExecutionService.list(query);
		int total = ruExecutionService.count(query);
		PageUtils pageUtils = new PageUtils(ruExecutionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("flowable:ruExecution:add")
	String add(){
	    return "flowable/ruExecution/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("flowable:ruExecution:edit")
	String edit(@PathVariable("id") String id,Model model){
		RuExecutionDO ruExecution = ruExecutionService.get(id);
		model.addAttribute("ruExecution", ruExecution);
	    return "flowable/ruExecution/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("flowable:ruExecution:add")
	public R save( RuExecutionDO ruExecution){
		if(ruExecutionService.save(ruExecution)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("flowable:ruExecution:edit")
	public R update( RuExecutionDO ruExecution){
		ruExecutionService.update(ruExecution);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("flowable:ruExecution:remove")
	public R remove( String id){
		if(ruExecutionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("flowable:ruExecution:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		ruExecutionService.batchRemove(ids);
		return R.ok();
	}
	
}
