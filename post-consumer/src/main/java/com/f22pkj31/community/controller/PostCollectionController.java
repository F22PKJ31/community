package com.f22pkj31.community.controller;

import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.entity.PostCollection;
import com.f22pkj31.community.service.PostCollectionClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/post")
public class PostCollectionController {


    @Autowired
    private PostCollectionClientService postCollectionClientService;

    @RequestMapping("saveCollection")
    public Object sendCollection(@RequestBody PostCollection postCollection) {
        return postCollectionClientService.sendCollection(postCollection.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("collectionList")
    public Object collectionList(@RequestBody PageIn<PostCollection> pageIn) {
        return postCollectionClientService.collectionList(pageIn);
    }

    @RequestMapping("deleteCollection")
    public Object deleteCollection(@RequestBody CommonId commonId) {
        return postCollectionClientService.deleteCollection(commonId);
    }

    @RequestMapping("collectionListByUserId")
    public Object collectionListByUserId(@RequestBody PageIn<PostCollection> pageIn) {
        return postCollectionClientService.collectionListByUserId(pageIn);
    }
}
