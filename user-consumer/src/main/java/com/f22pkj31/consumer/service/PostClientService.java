package com.f22pkj31.consumer.service;

import com.f22pkj31.community.entity.Post;
import com.f22pkj31.community.entity.PostCollection;
import com.f22pkj31.community.entity.PostComment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "post-provider")
@RequestMapping("/provider/post")
public interface PostClientService {

    @RequestMapping("freshPost")
    Object freshPost(@RequestBody Post post);

    @RequestMapping("freshComment")
    boolean freshComment(@RequestBody PostComment postComment);

    @RequestMapping("freshCollection")
    Object freshCollection(@RequestBody PostCollection postCollection);
}
