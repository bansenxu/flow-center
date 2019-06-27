package com.bootdo.system.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA. <br/>
 * User: 牛玉贤 <br/>
 * Date: 18-8-13 <br/>
 * Time: 下午5:31 <br/>
 * Email: ncc0706@gmail.com <br/>
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("error")
public class ErrorEndpoint {

    @GetMapping("403")
    public String accessDenied() {
        return "error/403";
    }

}
