package com.bootdo.modules.bz.company.controller;

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
import com.bootdo.modules.bz.account.domain.AccountDO;
import com.bootdo.modules.bz.account.service.AccountService;
import com.bootdo.modules.bz.company.domain.CompanyDO;
import com.bootdo.modules.bz.company.service.CompanyService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-19 10:44:05
 */
 
@Controller
@RequestMapping("/bz/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping()
//	@RequiresPermissions("bz:company:company")
	String Company(){
	    return "bz/company/company";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("bz:company:company")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CompanyDO> companyList = companyService.list(query);
		int total = companyService.count(query);
		PageUtils pageUtils = new PageUtils(companyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("bz:company:add")
	String add(){
	    return "bz/company/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("bz:company:edit")
	String edit(@PathVariable("id") String id,Model model){
		CompanyDO company = companyService.get(id);
		model.addAttribute("company", company);
	    return "bz/company/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("bz:company:add")
	public R save( CompanyDO company){
		if(companyService.save(company)>0){
			AccountDO account = new AccountDO();
			account.setComCode(company.getComCode());
			account.setAccountCode(company.getComCode());
			if(accountService.save(account)>0){
				return R.ok();
			}
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("bz:company:edit")
	public R update( CompanyDO company){
		companyService.update(company);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("bz:company:remove")
	public R remove( String id){
		if(companyService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("bz:company:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		companyService.batchRemove(ids);
		return R.ok();
	}
	
}
