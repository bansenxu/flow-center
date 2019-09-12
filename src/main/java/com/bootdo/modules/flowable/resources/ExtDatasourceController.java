package com.bootdo.modules.flowable.resources;

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
import com.bootdo.modules.flowable.domain.ExtDatasourceDO;
import com.bootdo.modules.flowable.service.ExtDatasourceService;

/**
 * 
 * 
 * @author wangxing
 * @email 358402521@qq.com
 * @date 2019-08-15 16:49:39
 */
 
@Controller
@RequestMapping("/flowable/extDatasource")
public class ExtDatasourceController {
	@Autowired
	private ExtDatasourceService extDatasourceService;
	
	@GetMapping()
	String ExtDatasource(){
	    return "flowable/extDatasource/extDatasource";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExtDatasourceDO> extDatasourceList = extDatasourceService.list(query);
		int total = extDatasourceService.count(query);
		PageUtils pageUtils = new PageUtils(extDatasourceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "flowable/extDatasource/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") String id,Model model){
		ExtDatasourceDO extDatasource = extDatasourceService.get(id);
		model.addAttribute("extDatasource", extDatasource);
	    return "flowable/extDatasource/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( ExtDatasourceDO extDatasource){
		if(extDatasourceService.save(extDatasource)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( ExtDatasourceDO extDatasource){
		extDatasourceService.update(extDatasource);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( String id){
		if(extDatasourceService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") String[] ids){
		extDatasourceService.batchRemove(ids);
		return R.ok();
	}
	
}
