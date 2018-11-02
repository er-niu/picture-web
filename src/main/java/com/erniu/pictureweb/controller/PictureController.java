package com.erniu.pictureweb.controller;

import com.alibaba.fastjson.JSON;
import com.erniu.pictureweb.handler.PictureShowHandler;
import com.erniu.pictureweb.model.PictureItem;
import com.erniu.pictureweb.model.page.PageImpl;
import com.erniu.pictureweb.service.PictureService;
import com.erniu.pictureweb.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;

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
    public Object findById(@PathVariable Long id) {
        PictureItem pictureItem = pictureService.getPicById(id);
        return JSON.toJSON(pictureItem);
    }

    @GetMapping("/search/picture/{pageNum}/{pageSize}/{title}")
    public Object searchChosenPic(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @PathVariable String title) {
        LOGGER.debug("-----findChosenPic received param pageNum:{},pageSize:{}, title:{}", pageNum, pageSize, title);
        PageImpl<PictureItem> picList = pictureService.getChosenPic(title, null, pageNum, pageSize);
        PictureShowHandler.handlePictureShow(picList.getElements());
        return JSON.toJSON(picList);
    }

    @GetMapping("/chosen/picture/{pageNum}/{pageSize}")
    public Object findChosenPic(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        LOGGER.debug("-----findChosenPic received param pageNum:{},pageSize:{}, title:{}", pageNum);
        PageImpl<PictureItem> picList = pictureService.getChosenPic(null, null, pageNum, pageSize);
        PictureShowHandler.handlePictureShow(picList.getElements());
        return JSON.toJSON(picList);
    }

    @RequestMapping("/query/picture")
    public Object findPicByType(@PathParam("title") String title, @PathParam("picType") Integer picType,
                                @PathParam("pageNum") Integer pageNum, @PathParam("pageSize") Integer pageSize) {
        LOGGER.debug("-----findPicByType received param title:{} picType:{} pageNum:{},pageSize:{}", title, picType, pageNum, pageSize);
        PageImpl<PictureItem> picList = pictureService.getPicByType(title, picType, pageNum, pageSize);
        PictureShowHandler.handlePictureShow(picList.getElements());
        return JSON.toJSON(picList);
    }

    @RequestMapping("/add")
    public void save(PictureItem item) {
        LOGGER.debug("-----received param item:{}", item.toString());
        pictureService.insertPic(item);
    }

    @RequestMapping("/like/picture/{id}")
    public void likePic(@PathVariable Long id) {
        LOGGER.debug("-----likePic received param id:{}", id);
        pictureService.likePic(id);
    }

    @RequestMapping("/like/picture/remove/{id}")
    public void removeLikePic(@PathVariable Long id) {
        LOGGER.debug("-----removeLikePic received param id:{}", id);
        pictureService.removeLikePic(id);
    }

    @RequestMapping("/del/picture/bytime/")
    public int delPictureByTime(@PathParam("startTime") String startTime, @PathParam("endTime") String endTime) {
        LOGGER.debug("-----delPictureByTime received param startTime:{} endTime:{}", startTime, endTime);
        Integer integer = pictureService.delPictureByTime(startTime, endTime);
        return integer;
    }

}
