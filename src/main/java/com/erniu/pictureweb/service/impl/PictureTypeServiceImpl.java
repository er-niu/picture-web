package com.erniu.pictureweb.service.impl;

import com.erniu.pictureweb.mapper.PictureTypeMapper;
import com.erniu.pictureweb.model.PictureType;
import com.erniu.pictureweb.service.PictureTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ErNiu on 2018/6/20.
 */
@Service
@Slf4j
public class PictureTypeServiceImpl implements PictureTypeService {

    @Autowired
    private PictureTypeMapper pictureTypeMapper;

    @Override
    public PictureType getPicTypeById(Long id) {
        try {
            return pictureTypeMapper.getById(id);
        } catch (Exception e) {
            log.error("failed to get picture type by id", e);
        }
        return null;
    }

    @Override
    public List<PictureType> getPictureType(Integer isShown) {
        try {
            return pictureTypeMapper.getPictureType(isShown);
        } catch (Exception e) {
            log.error("failed to get picture type", e);
        }
        return null;
    }

    @Override
    public void insertPicType(PictureType pictureType) {
        try {
            pictureTypeMapper.insert(pictureType);
        } catch (Exception e) {
            log.error("failed to insert picture type", e);
        }
    }
}
