package com.erniu.pictureweb.handler;

import com.erniu.pictureweb.model.PictureItem;
import com.erniu.pictureweb.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by qianqian.niu on 2018/9/25.
 */
@Slf4j
public class PictureShowHandler {

    public static void handlePictureShow(List<PictureItem> list){
        if (CollectionUtils.isEmpty(list)){
            return;
        }
        list.forEach(item -> item.setShowTime(Constants.simpleDateFormat.format(item.getCreateTime())));
    }

}
