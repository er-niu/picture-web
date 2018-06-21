package com.erniu.pictureweb.controller;

import com.alibaba.fastjson.JSON;
import com.erniu.pictureweb.model.PictureItem;
import com.erniu.pictureweb.model.page.PageImpl;
import com.erniu.pictureweb.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description : 图片浏览http接口
 * @Author ErNiu
 * @Date 2018/6/20 18:02
 */
@Controller
public class PictureController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PictureController.class);

    @Autowired
    private PictureService pictureService;

    @GetMapping("/picture/{id}")
    @ResponseBody
    public String findById(@PathVariable Long id) {
        PictureItem pictureItem = pictureService.getMaxPic(id);
        return pictureItem.toString();
    }

    @GetMapping("/chosen/picture/{pageNum}/{pageSize}")
    @ResponseBody
    public Object findByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        LOGGER.info("-----received param pageNum:{},pageSize:{}", pageNum, pageSize);
        PageImpl<PictureItem> picList = pictureService.getChosenPic(pageNum, pageSize);
        return JSON.toJSON(picList);
    }
}
