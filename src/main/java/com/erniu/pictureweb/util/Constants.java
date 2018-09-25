package com.erniu.pictureweb.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

/**
 * Created by ErNiu on 8/16/16.
 */
public class Constants {
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 15;
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 逗号
     */
    public static final String COMMA = ",";
    /**
     * 分号
     */
    public static final String SEMICOLON = ";";
    /**
     * 下划线
     */
    public static final String UNDERLINE = "_";
}
