package com.bootdo.modules.flowable.resources;

import java.util.List;
import java.util.Map;

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

import com.bootdo.modules.flowable.domain.HiTaskinstDO;
import com.bootdo.modules.flowable.service.HiTaskinstService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-13 14:52:13
 */
 
@Controller
@RequestMapping("/flowable/hiTaskinst")
public class HiTaskinstController  extends  BaseController{
	@Autowired
	private HiTaskinstService hiTaskinstService;
	
	@GetMapping()
//	@RequiresPermissions("flowable:hiTaskinst:hiTaskinst")
	String HiTaskinst(){
	    return "flowable/hiTaskinst/hiTaskinst";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("flowable:hiTaskinst:hiTaskinst")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//默认用户传入a
		params.put("userId",getUsername());
		logger.info("username:{}",getUsername());
		//查询列表数据
        Query query = new Query(params);
		List<Map<String, Object>> hiTaskinstList = hiTaskinstService.list(query);
		int total = hiTaskinstService.count(query);
		PageUtils pageUtils = new PageUtils(hiTaskinstList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("flowable:hiTaskinst:add")
	String add(){
	    return "flowable/hiTaskinst/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("flowable:hiTaskinst:edit")
	String edit(@PathVariable("id") String id,Model model){
		HiTaskinstDO hiTaskinst = hiTaskinstService.get(id);
		model.addAttribute("hiTaskinst", hiTaskinst);
	    return "flowable/hiTaskinst/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("flowable:hiTaskinst:add")
	public R save( HiTaskinstDO hiTaskinst){
		if(hiTaskinstService.save(hiTaskinst)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("flowable:hiTaskinst:edit")
	public R update( HiTaskinstDO hiTaskinst){
		hiTaskinstService.update(hiTaskinst);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("flowable:hiTaskinst:remove")
	public R remove( String id){
		if(hiTaskinstService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("flowable:hiTaskinst:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		hiTaskinstService.batchRemove(ids);
		return R.ok();
	}
	
}
