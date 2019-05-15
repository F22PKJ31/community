package com.f22pkj31.consumer.controller;

import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.entity.PostCollection;
import com.f22pkj31.consumer.service.PostClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/post")
public class PostCollectionController {


    @Autowired
    private PostClientService postClientService;

    @RequestMapping("saveCollection")
    public Object sendCollection(@RequestBody PostCollection postCollection) {
        return postClientService.sendCollection(postCollection.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("collectionList")
    public Object collectionList(@RequestBody PageIn<PostCollection> pageIn) {
        return postClientService.collectionList(pageIn);
    }

    @RequestMapping("deleteCollection")
    public Object deleteCollection(@RequestBody CommonId commonId) {
        return postClientService.deleteCollection(commonId);
    }

    @RequestMapping("collectionListByUserId")
    public Object collectionListByUserId(@RequestBody PageIn<PostCollection> pageIn) {
        return postClientService.collectionListByUserId(pageIn);
    }
}
