package com.bootdo.common.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class HttpServletUtils {

    /**
     * 判断是否三ajax请求<br/>
     *
     * @param req
     * @return
     */
    public static boolean jsAjax(HttpServletRequest req) {

        //判断是否为ajax请求，默认不是
        boolean isAjaxRequest = false;

        if (!StringUtils.isEmpty(req.getHeader("x-requested-with")) && req.getHeader("x-requested-with").equals("XMLHttpRequest")) {
            isAjaxRequest = true;
        }
        return isAjaxRequest;
    }
}
