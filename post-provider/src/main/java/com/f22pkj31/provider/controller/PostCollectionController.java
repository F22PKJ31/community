package com.f22pkj31.provider.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.entity.PostCollection;
import com.f22pkj31.provider.service.IPostCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider/post")
public class PostCollectionController {

    @Autowired
    private IPostCollectionService postCollectionService;

    @RequestMapping("collectionList")
    public IPage<PostCollection> collectionList(@RequestBody PageIn<PostCollection> pageIn) {
        return postCollectionService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                new QueryWrapper<PostCollection>().like("post_title", pageIn.getT().getPostTitle() == null ? "" : pageIn.getT().getPostTitle())
                        .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName()).orderByDesc("create_time"));
    }

    @RequestMapping("deleteCollection")
    public Object deleteCollection(@RequestBody CommonId commonId) {
        return postCollectionService.removeById(commonId.getId());
    }

    @RequestMapping("collectionListByUserId")
    public Object collectionListByUserId(@RequestBody PageIn<PostCollection> pageIn) {
        return postCollectionService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), new QueryWrapper<>(pageIn.getT()).orderByDesc("create_time"));
    }

    @RequestMapping("saveCollection")
    public Object sendCollection(@RequestBody PostCollection postCollection) {
        return postCollectionService.save(postCollection);
    }

    @RequestMapping("freshCollection")
    public Object freshCollection(@RequestBody PostCollection postCollection) {
        if (postCollection.getUserId() != null) {
            postCollectionService.update(postCollection, new UpdateWrapper<PostCollection>().eq("userId", postCollection.getUserId()));
        }
        if (postCollection.getPostId() != null) {
            postCollectionService.update(postCollection, new UpdateWrapper<PostCollection>().eq("postId", postCollection.getPostId()));
        }
        return true;
    }

}
