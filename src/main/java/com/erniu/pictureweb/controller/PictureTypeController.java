package com.erniu.pictureweb.controller;

import com.alibaba.fastjson.JSON;
import com.erniu.pictureweb.model.PictureType;
import com.erniu.pictureweb.service.PictureTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description : 图片分类接口
 * @Author ErNiu
 * @Date 2018/6/20 18:02
 */
@Controller
@CrossOrigin
@Slf4j
public class PictureTypeController {

    @Autowired
    private PictureTypeService pictureTypeService;

    @RequestMapping("/query/picture/type")
    public Object getPictureType() {
        List<PictureType> picList = pictureTypeService.getPictureType(1);
        return JSON.toJSON(picList);
    }

    @RequestMapping("/add/picture/type")
    public void save(PictureType item) {
        log.debug("-----received param item:{}", item.toString());
        pictureTypeService.insertPicType(item);
    }

}
