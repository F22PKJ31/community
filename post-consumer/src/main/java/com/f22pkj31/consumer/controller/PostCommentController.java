package com.f22pkj31.consumer.controller;

import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.entity.PostComment;
import com.f22pkj31.consumer.service.PostClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/post")
public class PostCommentController {

    @Autowired
    private PostClientService postClientService;

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody PostComment postComment) {
        Object o = postClientService.sendComment(postComment.setCreateTime(LocalDateTime.now()));
        CommonId commonId = new CommonId();
        commonId.setId(postComment.getPostId());
        postClientService.addReadCount(commonId);
        return o;
    }

    @RequestMapping("commentList")
    public Object commentList(@RequestBody PageIn<PostComment> pageIn) {
        return postClientService.commentList(pageIn);
    }

    @RequestMapping("deleteComment")
    public Object deleteComment(@RequestBody CommonId commonId) {
        PostComment postComment = postClientService.commentDetail(commonId);
        CommonId id = new CommonId();
        id.setId(postComment.getPostId());
        postClientService.subReadCount(id);
        return postClientService.deleteComment(commonId);
    }

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId) {
        return postClientService.countComment(commonId);
    }

}
