package com.f22pkj31.community.controller;

import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.entity.PostComment;
import com.f22pkj31.community.service.PostClientService;
import com.f22pkj31.community.service.PostCommentClientService;
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

    @Autowired
    private PostCommentClientService postCommentClientService;

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody PostComment postComment) {
        Object o = postCommentClientService.sendComment(postComment.setCreateTime(LocalDateTime.now()));
        CommonId commonId = new CommonId();
        commonId.setId(postComment.getPostId());
        postClientService.addReadCount(commonId);
        return o;
    }

    @RequestMapping("commentList")
    public Object commentList(@RequestBody PageIn<PostComment> pageIn) {
        return postCommentClientService.commentList(pageIn);
    }

    @RequestMapping("deleteComment")
    public Object deleteComment(@RequestBody CommonId commonId) {
        PostComment postComment = postCommentClientService.commentDetail(commonId);
        CommonId id = new CommonId();
        id.setId(postComment.getPostId());
        postClientService.subReadCount(id);
        return postCommentClientService.deleteComment(commonId);
    }

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId) {
        return postCommentClientService.countComment(commonId);
    }

}
