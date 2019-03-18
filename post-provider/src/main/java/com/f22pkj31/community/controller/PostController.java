package com.f22pkj31.community.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.*;
import com.f22pkj31.community.service.IPostCollectionService;
import com.f22pkj31.community.service.IPostCommentService;
import com.f22pkj31.community.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
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

    @Autowired
    private IPostCollectionService postCollectionService;

    @RequestMapping("postList")
    public Object postList(@RequestBody PageIn<Post> pageIn) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>().like("title", pageIn.getT().getTitle() == null ? "" : pageIn.getT().getTitle())
                .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName()).orderByDesc("create_time");
        if (!ObjectUtils.isEmpty(pageIn.getT().getUserId())) {
            queryWrapper.eq("user_id", pageIn.getT().getUserId());
        }
        return postService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), queryWrapper);
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
        return postService.saveOrUpdate(post);
    }

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody PostComment postComment) {
        return postCommentService.save(postComment);
    }

    @RequestMapping("commentList")
    public IPage<PostComment> commentList(@RequestBody PageIn<PostComment> pageIn) {
        if (pageIn.getT().getPostId() != null) {
            return postCommentService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), new QueryWrapper<>(pageIn.getT()));
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

    @RequestMapping("collectionList")
    public IPage<PostCollection> collectionList(@RequestBody PageIn<PostCollection> pageIn) {
        return postCollectionService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                new QueryWrapper<PostCollection>().like("post_title", pageIn.getT().getPostTitle() == null ? "" : pageIn.getT().getPostTitle())
                        .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName()).orderByDesc("create_time"));
    }

    @RequestMapping("deleteCollection")
    public Object deleteCollection(@RequestBody CommonId commonId) {
        return postCollectionService.removeById(commonId.getId());
    }

    @RequestMapping("collectionListByUserId")
    public Object collectionListByUserId(@RequestBody PageIn<PostCollection> pageIn) {
        return postCollectionService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), new QueryWrapper<>(pageIn.getT()).orderByDesc("create_time"));
    }

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId) {
        return postCommentService.count(new QueryWrapper<PostComment>().eq("post_id", commonId.getId()));
    }

    @RequestMapping("postListOrderByRead")
    public Object postListOrderByRead(@RequestBody PageIn<Post> pageIn) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>().like("title", pageIn.getT().getTitle() == null ? "" : pageIn.getT().getTitle())
                .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName()).orderByDesc("read_count");
        if (!ObjectUtils.isEmpty(pageIn.getT().getUserId())) {
            queryWrapper.eq("user_id", pageIn.getT().getUserId());
        }
        return postService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), queryWrapper);
    }

    @RequestMapping("addReadCount")
    public void addReadCount(@RequestBody CommonId commonId) {
        Post post = postService.getById(commonId.getId());
        post.setReadCount(post.getReadCount() + 1);
        postService.updateById(post);
    }

    @RequestMapping("subReadCount")
    public void subReadCount(@RequestBody CommonId commonId) {
        Post post = postService.getById(commonId.getId());
        post.setReadCount(post.getReadCount() - 1);
        postService.updateById(post);
    }

    @RequestMapping("commentDetail")
    public PostComment commentDetail(@RequestBody CommonId commonId) {
        return postCommentService.getById(commonId.getId());
    }
}
