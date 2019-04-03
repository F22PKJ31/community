package com.f22pkj31.community.service;

import com.f22pkj31.community.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "blog-provider")
@RequestMapping("/provider/blog")
public interface BlogClientService {

    @RequestMapping("freshBlog")
    public Object freshBlog(@RequestBody Blog blog);
}
