package com.erniu.pictureweb.service.impl;

import com.erniu.pictureweb.mapper.PictureItemMapper;
import com.erniu.pictureweb.model.PictureItem;
import com.erniu.pictureweb.model.page.PageImpl;
import com.erniu.pictureweb.service.PictureService;
import com.erniu.pictureweb.util.Constants;
import com.erniu.pictureweb.util.CosClientUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureItemMapper pictureItemMapper;

    @Autowired
    private CosClientUtil cosClientUtil;

    @Override
    public PictureItem getPicById(Long id) {
        PictureItem item = pictureItemMapper.getById(id);
        return item;
    }

    @Override
    public PageImpl<PictureItem> getPicByType(String title, Integer picType, Integer pageNum, Integer pageSize) {
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
            ruleList = pictureItemMapper.getPicByType(title, picType, minIndex, pageSize);
            totalNumber = pictureItemMapper.getPicCount(title, picType, minIndex, pageSize);
        } catch (Exception e) {
            log.error("read picture form db failed", e);
        }
        return new PageImpl<>(pageNum, pageSize, totalNumber, ruleList);
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
            log.error("read picture form db failed", e);
        }
        return new PageImpl<>(pageNum, pageSize, totalNumber, ruleList);
    }

    @Override
    public void insertPic(PictureItem pictureItem) {
        try {
            pictureItem.setCreateTime(new Date());
            pictureItemMapper.insert(pictureItem);
        } catch (Exception e) {
            log.error("insert picture to db failed", e);
        }
    }

    @Override
    public void likePic(Long imgId) {
        try {
            pictureItemMapper.likePic(imgId);
        } catch (Exception e) {
            log.error("like picture failed", e);
        }
    }

    @Override
    public void removeLikePic(Long imgId) {
        try {
            PictureItem item = pictureItemMapper.getById(imgId);
            if (item == null || item.getLikeNum() == 0) {
                log.warn("the pic likeNum has been zero");
                return;
            }
            pictureItemMapper.removeLikePic(imgId);
        } catch (Exception e) {
            log.error("remove like picture failed", e);
        }
    }

    @Override
    public Integer delPictureByTime(String startTime, String endTime, Integer picType) {
        Integer delCount = 0;
        try {
            List<PictureItem> pictures = pictureItemMapper.getPictureByTime(startTime, endTime, picType);
            delCount = pictures.size();
            pictures.forEach(pictureItem -> {
                // 删除数据库图片
                Long picId = pictureItem.getId();
                pictureItemMapper.delete(picId);
                // 删除腾讯cos存储
                String key = String.valueOf(picId) + ".jpg";
                String smallKey = "small/" + String.valueOf(picId) + ".jpg";
                cosClientUtil.deleteObject(key);
                cosClientUtil.deleteObject(smallKey);
            });
        } catch (Exception e) {
            log.error("remove picture failed", e);
        }
        return delCount;
    }
}