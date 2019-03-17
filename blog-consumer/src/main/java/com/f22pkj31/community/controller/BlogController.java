package com.f22pkj31.community.controller;


import com.f22pkj31.community.entity.*;
import com.f22pkj31.community.service.BlogClientService;
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
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogClientService blogClientService;

    @RequestMapping("blogList")
    public Object blogList(@RequestBody PageIn<Blog> pageIn) {
        return blogClientService.blogList(pageIn);
    }

    @RequestMapping("sendBlog")
    public Object sendBlog(@RequestBody Blog blog) {
        return blogClientService.sendBlog(blog.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("deleteBlog")
    public Object deleteBlog(@RequestBody CommonId commonId) {
        return blogClientService.deleteBlog(commonId);
    }

    @RequestMapping("blogDetail")
    public Blog blogDetail(@RequestBody CommonId commonId) {
        return blogClientService.blogDetail(commonId);
    }

    @RequestMapping("updateBlog")
    public Object updateBlog(@RequestBody Blog blog) {
        return blogClientService.updateBlog(blog.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody BlogComment blogComment) {
        return blogClientService.sendComment(blogComment.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("commentList")
    public Object commentList(@RequestBody PageIn<BlogComment> pageIn) {
        return blogClientService.commentList(pageIn);
    }

    @RequestMapping("deleteComment")
    public Object deleteComment(@RequestBody CommonId commonId) {
        return blogClientService.deleteComment(commonId);
    }

    @RequestMapping("sendCollection")
    public Object sendCollection(@RequestBody BlogCollection blogCollection) {
        return blogClientService.sendCollection(blogCollection.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("collectionList")
    public Object collectionList(@RequestBody PageIn<BlogCollection> pageIn) {
        return blogClientService.collectionList(pageIn);
    }

    @RequestMapping("deleteCollection")
    public Object deleteCollection(@RequestBody CommonId commonId) {
        return blogClientService.deleteCollection(commonId);
    }

    @RequestMapping("collectionListByUserId")
    public Object collectionListByUserId(@RequestBody PageIn<BlogCollection> pageIn) {
        return blogClientService.collectionListByUserId(pageIn);
    }

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId) {
        return blogClientService.countComment(commonId);
    }
}
