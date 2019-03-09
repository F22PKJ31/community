package com.f22pkj31.community.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.entity.Post;
import com.f22pkj31.community.entity.PostComment;
import com.f22pkj31.community.service.IPostCommentService;
import com.f22pkj31.community.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author f22pkj31
 * @since 2019-02-23
 */
@RestController
@RequestMapping("/provider/post")
public class PostController {

    @Autowired
    private IPostService postService;

    @Autowired
    private IPostCommentService postCommentService;

    @RequestMapping("postList")
    public Object postList(@RequestBody PageIn<Post> pageIn) {
        return postService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), new QueryWrapper<>(pageIn.getT()));
    }

    @RequestMapping("sendPost")
    public Object sendPost(@RequestBody Post post) {
        return postService.save(post);
    }

    @RequestMapping("deletePost")
    public Object deletePost(@RequestBody CommonId commonId) {
        return postService.removeById(commonId.getId());
    }

    @RequestMapping("postDetail")
    public Post postDetail(@RequestBody CommonId commonId) {
        return postService.getById(commonId.getId());
    }

    @RequestMapping("updatePost")
    public Object updatePost(@RequestBody Post post) {
        return postService.updateById(post);
    }

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody PostComment postComment) {
        return postCommentService.save(postComment);
    }

    @RequestMapping("commentList")
    public IPage<PostComment> commentList(@RequestBody PageIn<PostComment> pageIn) {
        return postCommentService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), new QueryWrapper<>(pageIn.getT()));
    }

    @RequestMapping("deleteComment")
    public Object deleteComment(@RequestBody CommonId commonId) {
        return postCommentService.removeById(commonId.getId());
    }

}
