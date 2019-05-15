package com.f22pkj31.consumer.controller;


import com.f22pkj31.community.entity.*;
import com.f22pkj31.consumer.service.BlogClientService;
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

    @RequestMapping("allBlogList")
    public Object allBlogList(@RequestBody PageIn<Blog> pageIn) {
        return blogClientService.allBlogList(pageIn);
    }

    @RequestMapping("sendBlog")
    public Object sendBlog(@RequestBody Blog blog) {
        return blogClientService.sendBlog(blog.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("deleteBlog")
    public Object deleteBlog(@RequestBody CommonId commonId) {
        blogClientService.deleteComment(new BlogComment().setBlogId(commonId.getId()));
        return blogClientService.deleteBlog(commonId);
    }

    @RequestMapping("blogDetail")
    public Blog blogDetail(@RequestBody CommonId commonId) {
        return blogClientService.blogDetail(commonId);
    }

    @RequestMapping("updateBlog")
    public Object updateBlog(@RequestBody Blog blog) {
        blogClientService.updateBlog(blog.setCreateTime(LocalDateTime.now()));
        blogClientService.freshComment(new BlogComment().setBlogId(blog.getBlogId()).setBlogTitle(blog.getTitle()));
        blogClientService.freshCollection(new BlogCollection().setBlogId(blog.getBlogId()).setBlogTitle(blog.getTitle()));
        return true;
    }

    @RequestMapping("blogListOrderByRead")
    public Object blogListOrderByRead(@RequestBody PageIn<Blog> pageIn){
        return blogClientService.blogListOrderByRead(pageIn);
    }

    @RequestMapping("addReadCount")
    public void addReadCount(@RequestBody CommonId commonId) {
        blogClientService.addReadCount(commonId);
    }

    @RequestMapping("subReadCount")
    public void subReadCount(@RequestBody CommonId commonId) {
        blogClientService.subReadCount(commonId);
    }


}
