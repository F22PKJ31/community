package com.f22pkj31.consumer.controller;

import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.NewsCollection;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.consumer.service.NewsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/news")
public class NewsCollectionController {

    @Autowired
    private NewsClientService newsClientService;

    @RequestMapping("collectionList")
    public Object collectionList(@RequestBody PageIn<NewsCollection> pageIn) {
        return newsClientService.collectionList(pageIn);
    }

    @RequestMapping("deleteCollection")
    public Object deleteCollection(@RequestBody CommonId commonId) {
        return newsClientService.deleteCollection(commonId);
    }

    @RequestMapping("collectionListByUserId")
    public Object collectionListByUserId(@RequestBody PageIn<NewsCollection> pageIn) {
        return newsClientService.collectionListByUserId(pageIn);
    }

    @RequestMapping("saveCollection")
    public Object sendCollection(@RequestBody NewsCollection newsCollection) {
        return newsClientService.sendCollection(newsCollection.setCreateTime(LocalDateTime.now()));
    }
}
