package com.f22pkj31.consumer.service;

import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.entity.PostComment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "post-provider")
@RequestMapping("/provider/post")
public interface PostCommentClientService {

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
