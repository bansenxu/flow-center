package com.bootdo.modules.flowable.resources;

import com.bootdo.modules.flowable.domain.ActDeCategory;
import com.bootdo.modules.flowable.service.CategoryService;
import com.bootdo.modules.flowable.utils.TreeCategory;
import com.bootdo.modules.flowable.vo.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value="categoryIndex")
    public String index() {
        return "category/categoryIndex";
    }


    /**
     * 菜单列表查询
     * @return
     */
    @RequestMapping(value="/getCategoryTree",method= RequestMethod.POST)
    @ResponseBody
    public SimpleResult getCategoryTree(){
        SimpleResult result = new SimpleResult();
        try {
            List<ActDeCategory> list = categoryService.getCategoryList();
            TreeCategory tree = new TreeCategory(list);
            Map map = new HashMap();
            map.put("tree",tree.buildTree());
            map.put("menuList",list);
            result.setData(map);
            result.setResult(1);
        }catch (Exception e){
            result.setResult(0);
            result.setMessage("请求异常");
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }


    /**
     * 菜单列表查询
     * @return
     */
    @RequestMapping(value="/getCategory",method= RequestMethod.POST)
    @ResponseBody
    public SimpleResult getCategory(ActDeCategory model){
        SimpleResult result = new SimpleResult();
        try {
            ActDeCategory data = categoryService.selectByPrimaryKey(model.getId());
            result.setData(data);
            result.setResult(1);
        }catch (Exception e){
            result.setResult(0);
            result.setMessage("请求异常");
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }


    /**
     * 菜单列表查询
     * @return
     */
    @RequestMapping(value="/saveCategory",method= RequestMethod.POST)
    @ResponseBody
    public SimpleResult saveCategory(ActDeCategory model){
        SimpleResult result = new SimpleResult();
        try {
            if(model.getId()!=null){
                categoryService.updateByPrimaryKeySelective(model);
            }else{
                categoryService.insert(model);
            }
            result.setResult(1);
        }catch (Exception e){
            result.setResult(0);
            result.setMessage("请求异常");
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

}
