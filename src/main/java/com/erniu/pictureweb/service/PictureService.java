package com.erniu.pictureweb.service;

import com.erniu.pictureweb.model.PictureItem;
import com.erniu.pictureweb.model.page.PageImpl;

/**
 * Created by ErNiu on 2018/6/20.
 */
public interface PictureService {
    PictureItem getPicById(Long id);

    PageImpl<PictureItem> getChosenPic(String title, Integer picTypoe, Integer pageNum, Integer pageSize);

    void insertPic(PictureItem pictureItem);
}
