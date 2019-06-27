package com.bootdo.system.endpoint;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.FileService;

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
}
