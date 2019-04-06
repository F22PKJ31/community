package com.f22pkj31.consumer.service;

import com.f22pkj31.community.entity.Blog;
import com.f22pkj31.community.entity.BlogCollection;
import com.f22pkj31.community.entity.BlogComment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "blog-provider")
@RequestMapping("/provider/blog")
public interface BlogClientService {

    @RequestMapping("freshBlog")
    public Object freshBlog(@RequestBody Blog blog);
    
    @RequestMapping("freshComment")
    public boolean freshComment(@RequestBody BlogComment blogComment);

    @RequestMapping("freshCollection")
    public Object freshCollection(@RequestBody BlogCollection blogCollection);
}
