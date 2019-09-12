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
import com.bootdo.modules.flowable.domain.ExtClassDO;
import com.bootdo.modules.flowable.service.ExtClassService;

/**
 * 
 * 
 * @author wangxing
 * @email 358402521@qq.com
 * @date 2019-08-15 15:59:38
 */
 
@Controller
@RequestMapping("/flowable/extClass")
public class ExtClassController {
	@Autowired
	private ExtClassService extClassService;
	
	@GetMapping()
	String ExtClass(){
	    return "flowable/extClass/extClass";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExtClassDO> extClassList = extClassService.list(query);
		int total = extClassService.count(query);
		PageUtils pageUtils = new PageUtils(extClassList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "flowable/extClass/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") String id,Model model){
		ExtClassDO extClass = extClassService.get(id);
		model.addAttribute("extClass", extClass);
	    return "flowable/extClass/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( ExtClassDO extClass){
		if(extClassService.save(extClass)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( ExtClassDO extClass){
		extClassService.update(extClass);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( String id){
		if(extClassService.remove(id)>0){
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
		extClassService.batchRemove(ids);
		return R.ok();
	}
	
}
