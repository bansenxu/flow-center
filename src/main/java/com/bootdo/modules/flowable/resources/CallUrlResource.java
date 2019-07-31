package com.bootdo.modules.flowable.resources;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.modules.flowable.domain.HttpUrlDO;
import com.bootdo.modules.flowable.service.HttpUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/flowable/httpUrl")
public class CallUrlResource extends BaseController {

    @Autowired
    private HttpUrlService httpUrlService;

    @GetMapping()
    String HttpUrl(){
        return "flowable/httpUrl/httpUrl";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<HttpUrlDO> httpUrlList = httpUrlService.list(query);
        int total = httpUrlService.count(query);
        PageUtils pageUtils = new PageUtils(httpUrlList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add(){
        return "flowable/httpUrl/add";
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") String id,Model model){
        HttpUrlDO httpUrl = httpUrlService.get(id);
        model.addAttribute("httpUrl", httpUrl);
        return "flowable/httpUrl/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save( HttpUrlDO httpUrl){
        if(httpUrlService.save(httpUrl)>0){
            return R.ok();
        }
        return R.error();
    }
    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update( HttpUrlDO httpUrl){
        httpUrlService.update(httpUrl);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping( "/remove")
    @ResponseBody
    public R remove( String id){
        if(httpUrlService.remove(id)>0){
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
        httpUrlService.batchRemove(ids);
        return R.ok();
    }
}
