package com.erniu.pictureweb.mapper;

import com.erniu.pictureweb.model.PictureItem;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Created by erniu on 2018/6/22.
 */
public interface PictureItemMapper {

    @Select("SELECT * FROM t_picture_item WHERE id = #{id}")
    PictureItem getById(Long id);

    @Select("<script>"
            + "SELECT * FROM `t_picture_item`"
            + "<where>"
            + "<if test='title != null'>"
            + "<bind name='title' value=\"'%' + title + '%'\" />"
            + " AND title like #{title}"
            + "</if>"
            + "<if test='picType !=null'>AND pic_type = #{picType}</if>"
            + "</where>"
            + "ORDER BY id DESC LIMIT #{minIndex},#{pageSize}"
            + "</script>")
    List<PictureItem> getPicByType(@Param(value = "title") String title, @Param(value = "picType") Integer picType,
                                   @Param(value = "minIndex") Integer minIndex, @Param(value = "pageSize") Integer pageSize);

    @Select("<script>"
            + "SELECT * FROM `t_picture_item`"
            + "<where>"
            + "<if test='title != null'>"
            + "<bind name='title' value=\"'%' + title + '%'\" />"
            + " AND title like #{title}"
            + "</if>"
            + "<if test='picType !=null'>AND pic_type = #{picType}</if>"
            + "</where>"
            + "ORDER BY like_num DESC LIMIT #{minIndex},#{pageSize}"
            + "</script>")
    List<PictureItem> getChosenPic(@Param(value = "title") String title, @Param(value = "picType") Integer picType,
                                   @Param(value = "minIndex") Integer minIndex, @Param(value = "pageSize") Integer pageSize);

    @Select("<script>"
            + "SELECT COUNT(1) FROM `t_picture_item`"
            + "<where>"
            + "<if test='title != null'>"
            + "<bind name='title' value=\"'%' + title + '%'\" />"
            + "AND title like #{title}"
            + "</if>"
            + "<if test='picType !=null'>AND pic_type = #{picType}</if>"
            + "</where>"
            + "</script>")
    Integer getPicCount(@Param(value = "title") String title, @Param(value = "picType") Integer picType,
                        @Param(value = "minIndex") Integer minIndex, @Param(value = "pageSize") Integer pageSize);

    @Insert("INSERT INTO t_picture_item(title,pic_desc,big_url,small_url,length,width,like_num,pic_type,create_time)" +
            "VALUES(#{title}, #{picDesc}, #{bigUrl}, #{smallUrl}, #{length}, #{width}, #{likeNum}, #{picType}, #{createTime})")
    void insert(PictureItem user);

    @Update("UPDATE t_picture_item SET title=#{title},pic_desc=#{picDesc},big_url=#{bigUrl},small_url=#{smallUrl}," +
            "length=#{length},width=#{width},like_num=#{likeNum},pic_type=#{picType},create_time=#{createTime} WHERE id =#{id}")
    void update(PictureItem user);

    @Delete("DELETE FROM t_picture_item WHERE id =#{id}")
    void delete(Long id);

    @Update("UPDATE t_picture_item SET like_num = like_num + 1 WHERE id =#{imgId}")
    void likePic(Long imgId);

    @Update("UPDATE t_picture_item SET like_num = like_num - 1 WHERE id =#{imgId}")
    void removeLikePic(Long imgId);

    @Select("<script>"
            + "SELECT * FROM `t_picture_item`"
            + "<where>"
            + "<if test='startTime !=null'>"
            + "AND create_time BETWEEN #{startTime} AND #{endTime}"
            + "</if>"
            + "<if test='picType !=null'>"
            + "AND pic_type = #{picType}"
            + "</if>"
            + "</where>"
            + "</script>")
    List<PictureItem> getPictureByTime(@Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime, @Param(value = "picType") Integer picType);
}
