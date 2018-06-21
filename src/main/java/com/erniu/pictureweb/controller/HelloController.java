package com.erniu.pictureweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by qianqian.niu on 2017/6/27.
 */
@Controller
public class HelloController {

    @GetMapping("/simple/{id}")
    public String findById(@PathVariable Long id) {
        return "hello erniu:" + id;
    }

    @GetMapping("/templates")
    String test(HttpServletRequest request) {
        //逻辑处理
        request.setAttribute("key", "hello world");
        return "/index";
    }
}
