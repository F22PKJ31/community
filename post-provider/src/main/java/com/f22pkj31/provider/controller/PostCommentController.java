package com.f22pkj31.provider.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.entity.PostComment;
import com.f22pkj31.provider.service.IPostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider/post")
public class PostCommentController {

    @Autowired
    private IPostCommentService postCommentService;

    @RequestMapping("commentList")
    public IPage<PostComment> commentList(@RequestBody PageIn<PostComment> pageIn) {
        if (pageIn.getT().getPostId() != null) {
            return postCommentService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), new QueryWrapper<>(pageIn.getT()).orderByDesc("create_time"));
        }
        return postCommentService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), new QueryWrapper<PostComment>()
                .like("post_title", pageIn.getT().getPostTitle() == null ? "" : pageIn.getT().getPostTitle())
                .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName())
                .orderByDesc("create_time"));
    }

    @RequestMapping("deleteComment")
    public Object deleteComment(@RequestBody CommonId commonId) {
        return postCommentService.removeById(commonId.getId());
    }

    @RequestMapping("deleteCommentList")
    public Object deleteComment(@RequestBody PostComment postComment) {
        return postCommentService.remove(new UpdateWrapper<>(postComment));
    }

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody PostComment postComment) {
        return postCommentService.save(postComment);
    }

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId) {
        return postCommentService.count(new QueryWrapper<PostComment>().eq("post_id", commonId.getId()));
    }

    @RequestMapping("commentDetail")
    public PostComment commentDetail(@RequestBody CommonId commonId) {
        return postCommentService.getById(commonId.getId());
    }

    @RequestMapping("freshComment")
    public boolean freshComment(@RequestBody PostComment postComment) {
        if (postComment.getPostId() != null) {
            postCommentService.update(postComment, new UpdateWrapper<PostComment>().eq("postId", postComment.getPostId()));
        }
        if (postComment.getCommentId() != null) {
            postCommentService.update(postComment, new UpdateWrapper<PostComment>().eq("userId", postComment.getUserId()));
        }
        return true;
    }

}
