package com.erniu.pictureweb.service;

import com.erniu.pictureweb.model.PictureItem;
import com.erniu.pictureweb.model.page.PageImpl;

/**
 * Created by ErNiu on 2018/6/20.
 */
public interface PictureService {
    PictureItem getMaxPic(long id);

    PageImpl<PictureItem> getChosenPic(Integer pageNum, Integer pageSize);
}
