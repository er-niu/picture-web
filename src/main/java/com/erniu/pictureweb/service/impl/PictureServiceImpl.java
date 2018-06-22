package com.erniu.pictureweb.service.impl;

import com.erniu.pictureweb.mapper.PictureItemMapper;
import com.erniu.pictureweb.model.PictureItem;
import com.erniu.pictureweb.model.page.PageImpl;
import com.erniu.pictureweb.service.PictureService;
import com.erniu.pictureweb.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ErNiu on 2018/6/20.
 */
@Service
public class PictureServiceImpl implements PictureService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PictureServiceImpl.class);

    @Autowired
    private PictureItemMapper pictureItemMapper;

    @Override
    public PictureItem getPicById(Long id) {
        PictureItem item = pictureItemMapper.getById(id);
        return item;
    }

    @Override
    public PageImpl<PictureItem> getChosenPic(String title, Integer picType, Integer pageNum, Integer pageSize) {
        if (pageSize < 1) {
            pageSize = Constants.DEFAULT_PAGE_SIZE;
        }
        if (pageNum < 1) {
            pageNum = Constants.DEFAULT_PAGE_NUMBER;
        }
        int minIndex = (pageNum - 1) * pageSize;
        int totalNumber = 0;

        List<PictureItem> ruleList = new ArrayList<>();
        try {
            //查询db状态
            ruleList = pictureItemMapper.getChosenPic(title, picType, minIndex, pageSize);
            totalNumber = pictureItemMapper.getPicCount(title, picType, minIndex, pageSize);
        } catch (Exception e) {
            LOGGER.error("read picture form db failed", e);
        }
        return new PageImpl<>(pageNum, pageSize, totalNumber, ruleList);
    }

    @Override
    public void insertPic(PictureItem pictureItem) {
        try {
            pictureItem.setCreateTime(new Date());
            pictureItemMapper.insert(pictureItem);
        } catch (Exception e) {
            LOGGER.error("insert picture to db failed", e);
        }
    }
}
