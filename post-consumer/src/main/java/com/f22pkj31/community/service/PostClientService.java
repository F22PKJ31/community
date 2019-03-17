package com.f22pkj31.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.f22pkj31.community.entity.*;
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

    @RequestMapping("sendComment")
    Object sendComment(@RequestBody PostComment postComment);

    @RequestMapping("commentList")
    Object commentList(@RequestBody PageIn<PostComment> pageIn);

    @RequestMapping("deleteComment")
    Object deleteComment(@RequestBody CommonId commonId);

    @RequestMapping("collectionList")
    IPage<PostCollection> collectionList(@RequestBody PageIn<PostCollection> pageIn);

    @RequestMapping("deleteCollection")
    Object deleteCollection(@RequestBody CommonId commonId);

    @RequestMapping("collectionListByUserId")
    Object collectionListByUserId(@RequestBody PageIn<PostCollection> pageIn);

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId);
}
