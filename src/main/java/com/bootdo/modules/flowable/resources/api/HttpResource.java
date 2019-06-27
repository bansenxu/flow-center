package com.bootdo.modules.flowable.resources.api;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flowable/api")
public class HttpResource {
    @ResponseBody
    @GetMapping("/test")
    public Object test() {
        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        map.put("data", list);
        return map;
    }
}
