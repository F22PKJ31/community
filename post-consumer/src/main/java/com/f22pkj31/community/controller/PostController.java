package com.f22pkj31.community.controller;


import com.f22pkj31.community.entity.*;
import com.f22pkj31.community.service.PostClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author f22pkj31
 * @since 2019-02-23
 */
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostClientService postClientService;

    @RequestMapping("postList")
    public Object postList(@RequestBody PageIn<Post> pageIn) {
        return postClientService.postList(pageIn);
    }

    @RequestMapping("sendPost")
    public Object sendPost(@RequestBody Post post) {
        return postClientService.sendPost(post.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("deletePost")
    public Object deletePost(@RequestBody CommonId commonId) {
        return postClientService.deletePost(commonId);
    }

    @RequestMapping("postDetail")
    public Post postDetail(@RequestBody CommonId commonId) {
        return postClientService.postDetail(commonId);
    }

    @RequestMapping("updatePost")
    public Object updatePost(@RequestBody Post post) {
        return postClientService.updatePost(post.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody PostComment postComment) {
        return postClientService.sendComment(postComment.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("commentList")
    public Object commentList(@RequestBody PageIn<PostComment> pageIn) {
        return postClientService.commentList(pageIn);
    }

    @RequestMapping("deleteComment")
    public Object deleteComment(@RequestBody CommonId commonId) {
        return postClientService.deleteComment(commonId);
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

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId) {
        return postClientService.countComment(commonId);
    }

}
