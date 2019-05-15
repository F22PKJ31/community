package com.f22pkj31.consumer.controller;


import com.f22pkj31.community.entity.*;
import com.f22pkj31.consumer.service.PostClientService;
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
    @RequestMapping("allPostList")
    public Object allPostList(@RequestBody PageIn<Post> pageIn) {
        return postClientService.allPostList(pageIn);
    }

    @RequestMapping("sendPost")
    public Object sendPost(@RequestBody Post post) {
        return postClientService.sendPost(post.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("deletePost")
    public Object deletePost(@RequestBody CommonId commonId) {
        postClientService.deleteComment(new PostComment().setPostId(commonId.getId()));
        return postClientService.deletePost(commonId);
    }

    @RequestMapping("postDetail")
    public Post postDetail(@RequestBody CommonId commonId) {
        return postClientService.postDetail(commonId);
    }

    @RequestMapping("updatePost")
    public Object updatePost(@RequestBody Post post) {
        postClientService.updatePost(post.setCreateTime(LocalDateTime.now()));
        postClientService.freshComment(new PostComment().setPostId(post.getPostId()).setPostTitle(post.getTitle()));
        postClientService.freshCollection(new PostCollection().setPostId(post.getPostId()).setPostTitle(post.getTitle()));
        return true;
    }

    @RequestMapping("postListOrderByRead")
    public Object postListOrderByRead(@RequestBody PageIn<Post> pageIn) {
        return postClientService.postListOrderByRead(pageIn);
    }

    @RequestMapping("addReadCount")
    public void addReadCount(@RequestBody CommonId commonId) {
        postClientService.addReadCount(commonId);
    }

    @RequestMapping("subReadCount")
    public void subReadCount(@RequestBody CommonId commonId) {
        postClientService.subReadCount(commonId);
    }

}
