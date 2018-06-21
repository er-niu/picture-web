package com.erniu.pictureweb.service.impl;

import com.erniu.pictureweb.model.PictureItem;
import com.erniu.pictureweb.model.page.PageImpl;
import com.erniu.pictureweb.service.PictureService;
import com.erniu.pictureweb.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ErNiu on 2018/6/20.
 */
@Service
public class PictureServiceImpl implements PictureService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PictureServiceImpl.class);

    @Override
    public PictureItem getMaxPic(long id) {
        PictureItem item = new PictureItem();
        item.setId(00001);
        item.setTitle("卡哇伊美女");
        item.setBigUrl("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-661501.jpg");
        item.setSmallUrl("https://alpha.wallhaven.cc/wallpapers/thumb/small/th-661501.jpg");
        item.setDesc("kawayi");
        item.setLength(3000);
        item.setWidth(2000);
        item.setCreateTime(new Date());
        return item;
    }

    @Override
    public PageImpl<PictureItem> getChosenPic(Integer pageNum, Integer pageSize) {
        if (pageSize < 1) {
            pageSize = Constants.DEFAULT_PAGE_SIZE;
        }
        if (pageNum < 1) {
            pageNum = Constants.DEFAULT_PAGE_NUMBER;
        }
        int minIndex = (pageNum - 1) * pageSize;
        int totalNumber = 0;

        List<PictureItem> ruleList = new ArrayList<>();
        try {
            //查询db状态
            // TODO
            PictureItem item = new PictureItem();
            item.setId(00001);
            item.setTitle("卡哇伊美女");
            item.setBigUrl("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-661501.jpg");
            item.setSmallUrl("https://alpha.wallhaven.cc/wallpapers/thumb/small/th-661501.jpg");
            item.setDesc("kawayi");
            item.setLength(3000);
            item.setWidth(2000);
            item.setCreateTime(new Date());

            PictureItem item1 = new PictureItem();
            item1.setId(00002);
            item1.setTitle("卡哇伊美女1");
            item1.setBigUrl("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-661502.jpg");
            item1.setSmallUrl("https://alpha.wallhaven.cc/wallpapers/thumb/small/th-661502.jpg");
            item1.setDesc("kawayi");
            item1.setLength(3000);
            item1.setWidth(2000);
            item1.setCreateTime(new Date());

            PictureItem item2 = new PictureItem();
            item2.setId(00003);
            item2.setTitle("卡哇伊美女2");
            item2.setBigUrl("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-661503.jpg");
            item2.setSmallUrl("https://alpha.wallhaven.cc/wallpapers/thumb/small/th-661503.jpg");
            item2.setDesc("kawayi");
            item2.setLength(3000);
            item2.setWidth(2000);
            item2.setCreateTime(new Date());

            PictureItem item3 = new PictureItem();
            item3.setId(00004);
            item3.setTitle("卡哇伊美女3");
            item3.setBigUrl("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-661504.jpg");
            item3.setSmallUrl("https://alpha.wallhaven.cc/wallpapers/thumb/small/th-661504.jpg");
            item3.setDesc("kawayi");
            item3.setLength(3000);
            item3.setWidth(2000);
            item3.setCreateTime(new Date());

            PictureItem item4 = new PictureItem();
            item4.setId(00005);
            item4.setTitle("卡哇伊美女4");
            item4.setBigUrl("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-661505.jpg");
            item4.setSmallUrl("https://alpha.wallhaven.cc/wallpapers/thumb/small/th-661505.jpg");
            item4.setDesc("kawayi");
            item4.setLength(3000);
            item4.setWidth(2000);
            item4.setCreateTime(new Date());

            PictureItem item5 = new PictureItem();
            item5.setId(00006);
            item5.setTitle("卡哇伊美女5");
            item5.setBigUrl("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-661506.jpg");
            item5.setSmallUrl("https://alpha.wallhaven.cc/wallpapers/thumb/small/th-661506.jpg");
            item5.setDesc("kawayi");
            item5.setLength(3000);
            item5.setWidth(2000);
            item5.setCreateTime(new Date());

            PictureItem item6 = new PictureItem();
            item6.setId(00007);
            item6.setTitle("卡哇伊美女6");
            item6.setBigUrl("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-661489.jpg");
            item6.setSmallUrl("https://alpha.wallhaven.cc/wallpapers/thumb/small/th-661489.jpg");
            item6.setDesc("kawayi");
            item6.setLength(3000);
            item6.setWidth(2000);
            item6.setCreateTime(new Date());

            PictureItem item7 = new PictureItem();
            item7.setId(00007);
            item7.setTitle("卡哇伊美女7");
            item7.setBigUrl("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-661500.jpg");
            item7.setSmallUrl("https://alpha.wallhaven.cc/wallpapers/thumb/small/th-661500.jpg");
            item7.setDesc("kawayi");
            item7.setLength(3000);
            item7.setWidth(2000);
            item7.setCreateTime(new Date());

            PictureItem item9 = new PictureItem();
            item9.setId(00007);
            item9.setTitle("卡哇伊美女9");
            item9.setBigUrl("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-661494.jpg");
            item9.setSmallUrl("https://alpha.wallhaven.cc/wallpapers/thumb/small/th-661494.jpg");

            ruleList.add(item);
            ruleList.add(item1);
            ruleList.add(item2);
            ruleList.add(item3);
            ruleList.add(item4);
            ruleList.add(item5);
            ruleList.add(item6);
            ruleList.add(item7);
            ruleList.add(item9);


            totalNumber = 10;
        } catch (Exception e) {
            LOGGER.error("read picture form db failed",e);
        }
        return new PageImpl<>(pageNum, pageSize, totalNumber, ruleList);
    }
}
