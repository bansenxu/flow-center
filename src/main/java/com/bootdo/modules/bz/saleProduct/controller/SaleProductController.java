package com.bootdo.modules.bz.saleProduct.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.modules.bz.product.domain.ProductDO;
import com.bootdo.modules.bz.saleProduct.domain.SaleProductDO;
import com.bootdo.modules.bz.saleProduct.service.SaleProductService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-20 09:43:14
 */
 
@RestController
@RequestMapping("/bz/sale_product")
public class SaleProductController {
	@Autowired
	private SaleProductService productService;
	
	@GetMapping()
//	@RequiresPermissions("bz:product:product")
	String Product(){
	    return "bz/sale_product/sale_product";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("bz:product:product")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SaleProductDO> productList = productService.list(query);
		int total = productService.count(query);
		PageUtils pageUtils = new PageUtils(productList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("bz:product:add")
	String add(){
	    return "bz/sale_product/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("bz:product:edit")
	String edit(@PathVariable("id") String id,Model model){
		SaleProductDO product = productService.get(id);
		model.addAttribute("product", product);
	    return "bz/sale_product/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("bz:product:add")
	public R save( SaleProductDO product){
		if(productService.save(product)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("bz:product:edit")
	public R update( SaleProductDO product){
		productService.update(product);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("bz:product:remove")
	public R remove( String id){
		if(productService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("bz:product:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		productService.batchRemove(ids);
		return R.ok();
	}
	
}
