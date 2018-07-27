package com.erniu.pictureweb.service;

import com.erniu.pictureweb.model.PictureItem;
import com.erniu.pictureweb.model.PictureType;

import java.util.List;

/**
 * Created by ErNiu on 2018/6/20.
 */
public interface PictureTypeService {
    PictureType getPicTypeById(Long id);

    List<PictureType> getPictureType(Integer isShown);

    void insertPicType(PictureType pictureType);
}
