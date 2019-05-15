package com.f22pkj31.consumer.service;

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

    @RequestMapping("allPostList")
    Object allPostList(@RequestBody PageIn<Post> pageIn);

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

    @RequestMapping("collectionList")
    IPage<PostCollection> collectionList(@RequestBody PageIn<PostCollection> pageIn);

    @RequestMapping("deleteCollection")
    Object deleteCollection(@RequestBody CommonId commonId);

    @RequestMapping("collectionListByUserId")
    Object collectionListByUserId(@RequestBody PageIn<PostCollection> pageIn);

    @RequestMapping("saveCollection")
    public Object sendCollection(@RequestBody PostCollection postCollection);

    @RequestMapping("freshCollection")
    public Object freshCollection(@RequestBody PostCollection postCollection);

    @RequestMapping("sendComment")
    Object sendComment(@RequestBody PostComment postComment);

    @RequestMapping("commentList")
    Object commentList(@RequestBody PageIn<PostComment> pageIn);

    @RequestMapping("deleteComment")
    Object deleteComment(@RequestBody CommonId commonId);

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId);

    @RequestMapping("commentDetail")
    public PostComment commentDetail(@RequestBody CommonId commonId);

    @RequestMapping("freshComment")
    public boolean freshComment(@RequestBody PostComment postComment);

    @RequestMapping("deleteCommentList")
    public Object deleteComment(@RequestBody PostComment postComment);


}
