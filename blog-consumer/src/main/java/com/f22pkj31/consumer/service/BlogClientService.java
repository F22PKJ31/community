package com.f22pkj31.consumer.service;

import com.f22pkj31.community.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "blog-provider")
@RequestMapping("/provider/blog")
public interface BlogClientService {

    @RequestMapping("blogList")
    Object blogList(@RequestBody PageIn<Blog> pageIn);

    @RequestMapping("allBlogList")
    Object allBlogList(@RequestBody PageIn<Blog> pageIn);

    @RequestMapping("sendBlog")
    Object sendBlog(@RequestBody Blog blog);

    @RequestMapping("deleteBlog")
    Object deleteBlog(@RequestBody CommonId commonId);

    @RequestMapping("blogDetail")
    Blog blogDetail(@RequestBody CommonId commonId);

    @RequestMapping("updateBlog")
    Object updateBlog(@RequestBody Blog blog);

    @RequestMapping("blogListOrderByRead")
    Object blogListOrderByRead(@RequestBody PageIn<Blog> pageIn);

    @RequestMapping("addReadCount")
    void addReadCount(@RequestBody CommonId commonId);

    @RequestMapping("subReadCount")
    void subReadCount(@RequestBody CommonId commonId);

    @RequestMapping("freshBlog")
    Object freshBlog(@RequestBody Blog blog);

    @RequestMapping("deleteCollect")
    boolean deleteCollect(@RequestBody CommonId commonId);

    @RequestMapping("sendCollection")
    Object sendCollection(@RequestBody BlogCollection blogCollection);

    @RequestMapping("collectionList")
    Object collectionList(@RequestBody PageIn<BlogCollection> pageIn);

    @RequestMapping("deleteCollection")
    Object deleteCollection(@RequestBody CommonId commonId);

    @RequestMapping("collectionListByUserId")
    Object collectionListByUserId(@RequestBody PageIn<BlogCollection> pageIn);

    @RequestMapping("freshCollection")
    Object freshCollection(@RequestBody BlogCollection blogCollection);

    @RequestMapping("sendComment")
    Object sendComment(@RequestBody BlogComment blogComment);

    @RequestMapping("commentList")
    Object commentList(@RequestBody PageIn<BlogComment> pageIn);

    @RequestMapping("deleteComment")
    Object deleteComment(@RequestBody CommonId commonId);

    @RequestMapping("countComment")
    int countComment(@RequestBody CommonId commonId);

    @RequestMapping("freshComment")
    boolean freshComment(@RequestBody BlogComment blogComment);

    @RequestMapping("commentDetail")
    BlogComment commentDetail(@RequestBody CommonId commonId);

    @RequestMapping("deleteCommentList")
    Object deleteComment(@RequestBody BlogComment blogComment);


}
