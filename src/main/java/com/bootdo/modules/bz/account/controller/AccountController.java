package com.bootdo.modules.bz.account.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.modules.bz.account.domain.AccountDO;
import com.bootdo.modules.bz.account.service.AccountService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-19 10:43:14
 */
 
@RestController
@RequestMapping("/bz/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping()
//	@RequiresPermissions("bz:account:account")
	String Account(){
	    return "bz/account/account";
	}
	
	@CrossOrigin
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("bz:account:account")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AccountDO> accountList = accountService.list(query);
		int total = accountService.count(query);
		PageUtils pageUtils = new PageUtils(accountList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("bz:account:add")
	String add(){
	    return "bz/account/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("bz:account:edit")
	String edit(@PathVariable("id") String id,Model model){
		AccountDO account = accountService.get(id);
		model.addAttribute("account", account);
	    return "bz/account/edit";
	}
	
	/**
	 * 保存
	 */
	@CrossOrigin
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("bz:account:add")
	public R save( AccountDO account){
		if(accountService.save(account)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@CrossOrigin
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("bz:account:edit")
	public R update( AccountDO account){
		accountService.update(account);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@CrossOrigin
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("bz:account:remove")
	public R remove( String id){
		if(accountService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("bz:account:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		accountService.batchRemove(ids);
		return R.ok();
	}
	
}
