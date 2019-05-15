package com.f22pkj31.consumer.controller;

import com.f22pkj31.community.entity.BlogComment;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.consumer.service.BlogClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/blog")
public class BlogCommentController {

    @Autowired
    private BlogClientService blogClientService;

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody BlogComment blogComment) {
        CommonId commonId = new CommonId();
        commonId.setId(blogComment.getBlogId());
        blogClientService.addReadCount(commonId);
        return blogClientService.sendComment(blogComment.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("commentList")
    public Object commentList(@RequestBody PageIn<BlogComment> pageIn) {
        return blogClientService.commentList(pageIn);
    }

    @RequestMapping("deleteComment")
    public Object deleteComment(@RequestBody CommonId commonId) {
        BlogComment blogComment = blogClientService.commentDetail(commonId);
        CommonId id = new CommonId();
        id.setId(blogComment.getBlogId());
        blogClientService.subReadCount(id);
        return blogClientService.deleteComment(commonId);
    }

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId) {
        return blogClientService.countComment(commonId);
    }
}
