package com.bootdo.system.endpoint;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.FileService;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

/**
 * Created by IntelliJ IDEA. <br/>
 * User: 牛玉贤 <br/>
 * Date: 2018/7/25 <br/>
 * Time: 23:06 <br/>
 * Email: ncc0706@gmail.com <br/>
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class DashboardEndpoint extends AbstractEndpoint {

    @Autowired
    private MenuService menuService;

    @Autowired
    private FileService fileService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 注销登陆用户<br/>
     *
     * @return
     */
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping(value = {"", "/"})
    public String home() {
        return "redirect:/dashboard";
    }

    @Log("请求访问主页")
    @GetMapping(value = "dashboard")
    public String dashboard(Model model, Principal principal) {

        String username = principal.getName();
        UserDO userDO = getUser();
        List<Tree<MenuDO>> menus = menuService.listMenuTree(userDO.getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("name", username);
        FileDO fileDO = fileService.get(userDO.getPicId());
        if (fileDO != null && fileDO.getUrl() != null) {
            if (fileService.isExist(fileDO.getUrl())) {
                model.addAttribute("picUrl", fileDO.getUrl());
            } else {
                model.addAttribute("picUrl", "/img/photo_s.jpg");
            }
        } else {
            model.addAttribute("picUrl", "/img/photo_s.jpg");
        }
        model.addAttribute("username", username);
        return "dashboard";
    }

}
