package com.f22pkj31.consumer.service;

import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.entity.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "post-provider")
@RequestMapping("/provider/post")
public interface PostClientService {

    @RequestMapping("postList")
    Object postList(@RequestBody PageIn<Post> pageIn);

    @RequestMapping("sendPost")
    Object sendPost(@RequestBody Post post);

    @RequestMapping("deletePost")
    Object deletePost(@RequestBody CommonId commonId);

    @RequestMapping("postDetail")
    Post postDetail(@RequestBody CommonId commonId);

    @RequestMapping("updatePost")
    Object updatePost(@RequestBody Post post);

    @RequestMapping("postListOrderByRead")
    public Object postListOrderByRead(@RequestBody PageIn<Post> pageIn);

    @RequestMapping("addReadCount")
    public void addReadCount(@RequestBody CommonId commonId);

    @RequestMapping("subReadCount")
    public void subReadCount(@RequestBody CommonId commonId);

    @RequestMapping("freshPost")
    public Object freshPost(@RequestBody Post post);


}
