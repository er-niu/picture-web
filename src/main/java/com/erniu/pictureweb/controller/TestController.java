package com.erniu.pictureweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @GetMapping("/classify/{picType}")
    public String findByClassify(@PathVariable Long picType, HttpServletRequest request) {
        request.setAttribute("picType", picType);
        return "classify";
    }

}