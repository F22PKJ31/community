package com.f22pkj31.community.service;

import com.f22pkj31.community.entity.Blog;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
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

    @RequestMapping("blogListOrderByRead")
    public Object blogListOrderByRead(@RequestBody PageIn<Blog> pageIn);

    @RequestMapping("addReadCount")
    public void addReadCount(@RequestBody CommonId commonId);

    @RequestMapping("subReadCount")
    public void subReadCount(@RequestBody CommonId commonId);

    @RequestMapping("freshBlog")
    public Object freshBlog(@RequestBody Blog blog);

}
