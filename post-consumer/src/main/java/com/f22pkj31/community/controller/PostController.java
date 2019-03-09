package com.f22pkj31.community.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.entity.Post;
import com.f22pkj31.community.entity.PostComment;
import com.f22pkj31.community.service.PostClientService;
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
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostClientService postClientService;

    @RequestMapping("postList")
    public Object postList(@RequestBody PageIn<Post> pageIn) {
        return postClientService.postList(pageIn);
    }

    @RequestMapping("sendPost")
    public Object sendPost(@RequestBody Post post) {
        return postClientService.sendPost(post);
    }

    @RequestMapping("deletePost")
    public Object deletePost(@RequestBody CommonId commonId) {
        return postClientService.deletePost(commonId);
    }

    @RequestMapping("postDetail")
    public Post postDetail(@RequestBody CommonId commonId) {
        return postClientService.postDetail(commonId);
    }

    @RequestMapping("updatePost")
    public Object updatePost(@RequestBody Post post) {
        return postClientService.updatePost(post);
    }

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody PostComment postComment) {
        return postClientService.sendComment(postComment);
    }

    @RequestMapping("commentList")
    public IPage<PostComment> commentList(@RequestBody PageIn<PostComment> pageIn) {
        return postClientService.commentList(pageIn);
    }

    @RequestMapping("deleteComment")
    public Object deleteComment(@RequestBody CommonId commonId) {
        return postClientService.deleteComment(commonId);
    }

    @RequestMapping("deleteCollect")
    public boolean deleteCollect(@RequestBody CommonId commonId) {
        return postClientService.deleteCollect(commonId);
    }

}
