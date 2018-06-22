package com.erniu.pictureweb.enums;

/**
 * @Description : 图片类型枚举类
 * @Author ErNiu
 * @Date 2018/6/22 9:42
 */
public enum PictureTypeEnum {
    GIRL(0, "小姐姐"), BOY(1, "小哥哥"), ANIME(2, "动漫"), SCENERY(3, "风景"), CAR(5, "名车"), TEXT(6, "文字控"), ANIMAL(0, "萌宠");

    private int value;
    private String label;

    PictureTypeEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }
}
