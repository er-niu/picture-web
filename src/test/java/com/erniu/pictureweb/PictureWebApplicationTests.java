package com.erniu.pictureweb;

import com.erniu.pictureweb.model.PictureItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PictureWebApplicationTests {

    @Test
    public void contextLoads() {
        List<String> userForumIds = new ArrayList<>();
        userForumIds.add("123");
        userForumIds.add("456");

        PictureItem item1 = new PictureItem();
        item1.setTitle("123");
        PictureItem item2 = new PictureItem();
        item2.setTitle("456");
        PictureItem item3 = new PictureItem();
        item3.setTitle("789");
        List<PictureItem> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);

        list.stream().filter(item -> userForumIds.contains(item.getTitle())).forEach(item -> item.setBigUrl("http"));

        list.stream().forEach(item -> System.out.println(item.getBigUrl() + item.getTitle()));


    }


}
