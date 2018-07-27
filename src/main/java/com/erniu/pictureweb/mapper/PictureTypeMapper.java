package com.erniu.pictureweb.mapper;

import com.erniu.pictureweb.model.PictureType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by erniu on 2018/6/22.
 */
public interface PictureTypeMapper {

    @Select("SELECT * FROM t_picture_type WHERE id = #{id}")
    PictureType getById(Long id);

    @Select("<script>"
            + "SELECT * FROM `t_picture_type`"
            + "<where>"
            + "<if test='isShown !=null'>"
            + "AND is_shown = #{isShown}"
            + "</if>"
            + "</where>"
            + "ORDER BY weight_value DESC"
            + "</script>")
    List<PictureType> getPictureType(@Param(value = "isShown") Integer isShown);

    @Insert("INSERT INTO t_picture_type(id,type_name,is_shown,weight_value,create_time)" +
            "VALUES(#{id}, #{typeName}, #{isShown}, #{weightValue}, #{createTime})")
    void insert(PictureType pictureType);

    @Delete("DELETE FROM t_picture_type WHERE id =#{id}")
    void delete(Long id);


}
