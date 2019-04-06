package com.f22pkj31.consumer.controller;

import com.f22pkj31.community.entity.BlogCollection;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.consumer.service.BlogCollectionClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/blog")
public class BlogCollectionController {

    @Autowired
    private BlogCollectionClientService blogCollectionClientService;

    @RequestMapping("saveCollection")
    public Object saveCollection(@RequestBody BlogCollection blogCollection) {
        return blogCollectionClientService.sendCollection(blogCollection.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("collectionList")
    public Object collectionList(@RequestBody PageIn<BlogCollection> pageIn) {
        return blogCollectionClientService.collectionList(pageIn);
    }

    @RequestMapping("deleteCollection")
    public Object deleteCollection(@RequestBody CommonId commonId) {
        return blogCollectionClientService.deleteCollection(commonId);
    }

    @RequestMapping("collectionListByUserId")
    public Object collectionListByUserId(@RequestBody PageIn<BlogCollection> pageIn) {
        return blogCollectionClientService.collectionListByUserId(pageIn);
    }
}
