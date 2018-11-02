package com.erniu.pictureweb.service;

import com.erniu.pictureweb.model.PictureItem;
import com.erniu.pictureweb.model.page.PageImpl;

import java.util.Date;
import java.util.List;

/**
 * Created by ErNiu on 2018/6/20.
 */
public interface PictureService {
    PictureItem getPicById(Long id);

    PageImpl<PictureItem> getPicByType(String title, Integer picType, Integer pageNum, Integer pageSize);

    PageImpl<PictureItem> getChosenPic(String title, Integer picTypoe, Integer pageNum, Integer pageSize);

    /**
     * 插图图片
     * @param pictureItem
     */
    void insertPic(PictureItem pictureItem);

    /**
     * 图片点赞
     * @param imgId
     */
    void likePic(Long imgId);

    /**
     * 取消图片点赞
     * @param imgId
     */
    void removeLikePic(Long imgId);

    /**
     * 根据时间查询图片
     * @param startTime
     * @param endTime
     * @return
     */
    Integer delPictureByTime(String startTime, String endTime);
}
