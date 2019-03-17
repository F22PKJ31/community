package com.f22pkj31.community.service;

import com.f22pkj31.community.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "blog-provider")
@RequestMapping("/provider/blog")
public interface BlogClientService {

    @RequestMapping("blogList")
    Object blogList(@RequestBody PageIn<Blog> pageIn);

    @RequestMapping("sendBlog")
    Object sendBlog(@RequestBody Blog blog);

    @RequestMapping("deleteBlog")
    Object deleteBlog(@RequestBody CommonId commonId);

    @RequestMapping("blogDetail")
    Blog blogDetail(@RequestBody CommonId commonId);

    @RequestMapping("updateBlog")
    Object updateBlog(@RequestBody Blog blog);

    @RequestMapping("sendComment")
    Object sendComment(@RequestBody BlogComment blogComment);

    @RequestMapping("commentList")
    Object commentList(@RequestBody PageIn<BlogComment> pageIn);

    @RequestMapping("deleteComment")
    Object deleteComment(@RequestBody CommonId commonId);

    @RequestMapping("deleteCollect")
    public boolean deleteCollect(@RequestBody CommonId commonId);

    @RequestMapping("sendCollection")
    public Object sendCollection(@RequestBody BlogCollection blogCollection);

    @RequestMapping("collectionList")
    public Object collectionList(@RequestBody PageIn<BlogCollection> pageIn);

    @RequestMapping("deleteCollection")
    public Object deleteCollection(@RequestBody CommonId commonId);

    @RequestMapping("collectionListByUserId")
    public Object collectionListByUserId(@RequestBody PageIn<BlogCollection> pageIn);

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId);
}
