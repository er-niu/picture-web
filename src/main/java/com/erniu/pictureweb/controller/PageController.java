package com.erniu.pictureweb.controller;

import com.erniu.pictureweb.model.PictureType;
import com.erniu.pictureweb.service.PictureTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@CrossOrigin
@Slf4j
public class PageController {

    @Autowired
    private PictureTypeService pictureTypeService;

    @GetMapping("/")
    public String homePage(HttpServletRequest request, Model model) {
        List<PictureType> picList = pictureTypeService.getPictureType(1);
        model.addAttribute("typeList", picList);
        return "index";
    }

    @GetMapping("/like")
    public String likePic(HttpServletRequest request, Model model) {
        List<PictureType> picList = pictureTypeService.getPictureType(1);
        model.addAttribute("typeList", picList);
        return "like";
    }

    @GetMapping("/search/{title}")
    public String searchPic(@PathVariable String title, Model model) {
        log.info("received param:{}", title);
        List<PictureType> picList = pictureTypeService.getPictureType(1);
        model.addAttribute("typeList", picList);
        model.addAttribute("title", title);
        return "search";
    }

    @GetMapping("/classify/{picType}")
    public String findByClassify(@PathVariable Long picType, HttpServletRequest request, Model model) {
        List<PictureType> picList = pictureTypeService.getPictureType(1);
        model.addAttribute("picType", picType);
        model.addAttribute("typeList", picList);
        return "classify";
    }

}