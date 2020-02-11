package com.bootdo.modules.bz.saleorder.controller;

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
import com.bootdo.modules.bz.saleorder.domain.SaleOrderDO;
import com.bootdo.modules.bz.saleorder.service.SaleOrderService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-21 14:34:42
 */
 
@Controller
@RequestMapping("/bz/sale_order")
public class SaleOrderController {
	@Autowired
	private SaleOrderService orderService;
	
	@GetMapping()
//	@RequiresPermissions("flowable:order:order")
	String Order(){
	    return "bz/sale_order/sale_order";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("flowable:order:order")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SaleOrderDO> orderList = orderService.list(query);
		int total = orderService.count(query);
		PageUtils pageUtils = new PageUtils(orderList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("flowable:order:add")
	String add(){
	    return "bz/sale_order/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("flowable:order:edit")
	String edit(@PathVariable("id") String id,Model model){
		SaleOrderDO order = orderService.get(id);
		model.addAttribute("order", order);
	    return "bz/sale_order/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("flowable:order:add")
	public R save( SaleOrderDO order){
		if(orderService.save(order)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("flowable:order:edit")
	public R update( SaleOrderDO order){
		orderService.update(order);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("flowable:order:remove")
	public R remove( String id){
		if(orderService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("flowable:order:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		orderService.batchRemove(ids);
		return R.ok();
	}
	
}
