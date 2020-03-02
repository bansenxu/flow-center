package com.bootdo.modules.bz.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.modules.bz.product.domain.ProductDO;
import com.bootdo.modules.bz.product.service.ProductService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-18 17:03:39
 */
 
@RestController
@RequestMapping("/bz/product")
//@Api(tags = "BookController", description = "BookController | 通过书来测试swagger")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping()
	String Product(){
	    return "bz/product/product";
	}
	
	@CrossOrigin
	@ResponseBody
	@PostMapping("/list")
	public PageUtils list(@RequestBody Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProductDO> productList = productService.list(query);
		int total = productService.count(query);
		PageUtils pageUtils = new PageUtils(productList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "bz/product/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") String id,Model model){
		ProductDO product = productService.get(id);
		model.addAttribute("product", product);
	    return "bz/product/edit";
	}
	
	@PostMapping("/get/{id}")
	ProductDO get(@PathVariable("id") String id,Model model){
		ProductDO product = productService.get(id);
	    return product;
	}
	
	/**
	 * 保存
	 */
	@CrossOrigin
	@ResponseBody
	@PostMapping("/save")
	public R save(@RequestBody  ProductDO product){
		if(productService.save(product)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@CrossOrigin
	@ResponseBody
	@PostMapping("/update")
	public R update(@RequestBody  ProductDO product){
		productService.update(product);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@CrossOrigin
	@ResponseBody
	@PostMapping( "/remove")
	public R remove(@RequestParam("id") String id){
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
	public R remove(@RequestParam("ids[]") String[] ids){
		productService.batchRemove(ids);
		return R.ok();
	}
	
}
