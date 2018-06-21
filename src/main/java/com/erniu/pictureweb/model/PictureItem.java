package com.erniu.pictureweb.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Created by ErNiu on 2018/6/20.
 */
@Data
public class PictureItem {
    private long id;
    /**
     * 图片标题
     */
    private String title;
    /**
     * 图片介绍
     */
    private String desc;
    /**
     * 大图utl地址
     */
    private String bigUrl;
    /**
     * 缩略图url地址
     */
    private String smallUrl;
    /**
     * 图片高度
     */
    private long length;
    /**
     * 图片宽度
     */
    private long width;
    /**
     * 点赞数量
     */
    private long likeNum;
    /**
     * 图片更新时间
     */
    private Date createTime;
}
