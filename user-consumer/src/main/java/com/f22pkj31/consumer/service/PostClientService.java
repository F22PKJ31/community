package com.f22pkj31.consumer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.f22pkj31.community.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "post-provider")
@RequestMapping("/provider/post")
public interface PostClientService {

    @RequestMapping("freshPost")
    public Object freshPost(@RequestBody Post post);

    @RequestMapping("freshComment")
    public boolean freshComment(@RequestBody PostComment postComment);

    @RequestMapping("freshCollection")
    public Object freshCollection(@RequestBody PostCollection postCollection);
}
