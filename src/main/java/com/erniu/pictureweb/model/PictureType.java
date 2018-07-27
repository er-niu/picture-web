package com.erniu.pictureweb.model;

import lombok.Data;

/**
 * Created by ErNiu on 2018/6/20.
 */
@Data
public class PictureType {
    private Long id;
    /**
     * 图片类型名称
     */
    private String typeName;
    /**
     * 是否显示
     */
    private Integer isShown;
    /**
     * 权重
     */
    private Integer weightValue;
    /**
     * 创建时间;
     */
    private String createTime;
}
