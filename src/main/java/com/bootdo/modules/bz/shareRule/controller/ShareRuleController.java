package com.bootdo.modules.bz.shareRule.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.modules.bz.shareRule.domain.ShareRuleDO;
import com.bootdo.modules.bz.shareRule.service.ShareRuleService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-20 09:43:50
 */
 
@Controller
@RequestMapping("/bz/share_rule")
public class ShareRuleController {
	@Autowired
	private ShareRuleService ruleService;
	
	@GetMapping()
//	@RequiresPermissions("bz:rule:rule")
	String Rule(){
	    return "bz/share_rule/share_rule";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("bz:rule:rule")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ShareRuleDO> ruleList = ruleService.list(query);
		int total = ruleService.count(query);
		PageUtils pageUtils = new PageUtils(ruleList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("bz:rule:add")
	String add(){
	    return "bz/share_rule/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("bz:rule:edit")
	String edit(@PathVariable("id") String id,Model model){
		ShareRuleDO rule = ruleService.get(id);
		model.addAttribute("rule", rule);
	    return "bz/share_rule/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("bz:rule:add")
	public R save( ShareRuleDO rule){
		if(ruleService.save(rule)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("bz:rule:edit")
	public R update( ShareRuleDO rule){
		ruleService.update(rule);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("bz:rule:remove")
	public R remove( String id){
		if(ruleService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("bz:rule:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		ruleService.batchRemove(ids);
		return R.ok();
	}
	
}
