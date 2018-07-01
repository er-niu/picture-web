package com.erniu.pictureweb.controller;

import com.alibaba.fastjson.JSON;
import com.erniu.pictureweb.model.PictureItem;
import com.erniu.pictureweb.model.page.PageImpl;
import com.erniu.pictureweb.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * @Description : 图片浏览http接口
 * @Author ErNiu
 * @Date 2018/6/20 18:02
 */
@RestController
@CrossOrigin
public class PictureController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PictureController.class);

    @Autowired
    private PictureService pictureService;

    @GetMapping("/picture/{id}")
    public String findById(@PathVariable Long id) {
        PictureItem pictureItem = pictureService.getPicById(id);
        return pictureItem.toString();
    }

    @GetMapping("/chosen/picture/{pageNum}/{pageSize}")
    public Object findChosenPic(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        LOGGER.debug("-----received param pageNum:{},pageSize:{}", pageNum, pageSize);
        PageImpl<PictureItem> picList = pictureService.getChosenPic(null, 0, pageNum, pageSize);
        return JSON.toJSON(picList);
    }

    @RequestMapping("/query/picture")
    public Object findPicByType(@PathParam("title") String title, @PathParam("picType") Integer picType,
                                @PathParam("pageNum") Integer pageNum, @PathParam("pageSize") Integer pageSize) {
        LOGGER.debug("-----received param title:{} picType:{} pageNum:{},pageSize:{}", title, picType, pageNum, pageSize);
        PageImpl<PictureItem> picList = pictureService.getChosenPic(title, picType, pageNum, pageSize);
        return JSON.toJSON(picList);
    }

    @RequestMapping("/add")
    public void save(PictureItem item) {
        LOGGER.debug("-----received param item:{}", item.toString());
        pictureService.insertPic(item);
    }

}
