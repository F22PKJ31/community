package com.f22pkj31.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.BlogComment;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.service.IBlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/provider/blog")
public class BlogCommentController {

    @Autowired
    private IBlogCommentService blogCommentService;

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody BlogComment blogComment) {
        return blogCommentService.save(blogComment);
    }


    @RequestMapping("commentList")
    public IPage<BlogComment> commentList(@RequestBody PageIn<BlogComment> pageIn) {
        if (pageIn.getT().getBlogId() != null) {
            return blogCommentService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), new QueryWrapper<>(pageIn.getT()).orderByDesc("create_time"));
        }
        return blogCommentService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                new QueryWrapper<BlogComment>().like("blog_title", pageIn.getT().getBlogTitle() == null ? "" : pageIn.getT().getBlogTitle())
                        .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName()).orderByDesc("create_time"));
    }

    @RequestMapping("deleteComment")
    public Object deleteComment(@RequestBody CommonId commonId) {
        return blogCommentService.removeById(commonId.getId());
    }

    @RequestMapping("deleteCommentList")
    public Object deleteComment(@RequestBody BlogComment blogComment) {
        return blogCommentService.remove(new UpdateWrapper<>(blogComment));
    }

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId) {
        return blogCommentService.count(new QueryWrapper<BlogComment>().eq("blog_id", commonId.getId()));
    }

    @RequestMapping("commentDetail")
    public BlogComment commentDetail(@RequestBody CommonId commonId) {
        return blogCommentService.getById(commonId.getId());
    }

    @RequestMapping("freshComment")
    public boolean freshComment(@RequestBody BlogComment blogComment) {
        if (blogComment.getBlogId() != null) {
            blogCommentService.update(blogComment, new UpdateWrapper<BlogComment>().eq("blogId", blogComment.getBlogId()));
        }
        if (blogComment.getCommentId() != null) {
            blogCommentService.update(blogComment, new UpdateWrapper<BlogComment>().eq("userId", blogComment.getUserId()));
        }
        return true;
    }
}
