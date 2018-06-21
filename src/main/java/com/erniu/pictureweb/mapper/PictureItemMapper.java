package com.erniu.pictureweb.mapper;

import com.erniu.pictureweb.model.PictureItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by songxiao on 2018/6/22.
 */
public interface PictureItemMapper {


    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex",  column = "user_sex"),
            @Result(property = "nickName", column = "nick_name")
    })
    List<PictureItem> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex",  column = "user_sex"),
            @Result(property = "nickName", column = "nick_name")
    })
    PictureItem getOne(Long id);

    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(PictureItem user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(PictureItem user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);



}
