package com.f22pkj31.provider.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.entity.Post;
import com.f22pkj31.provider.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
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

    @RequestMapping("postList")
    public Object postList(@RequestBody PageIn<Post> pageIn) {
        QueryWrapper<Post> queryWrapper = getPostQueryWrapper(pageIn);
        queryWrapper.eq("state", 1).orderByDesc("create_time");
        return postService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), queryWrapper);
    }

    @RequestMapping("allPostList")
    public Object allPostList(@RequestBody PageIn<Post> pageIn) {
        QueryWrapper<Post> queryWrapper = getPostQueryWrapper(pageIn);
        if(!StringUtils.isEmpty(pageIn.getT().getState())){
            queryWrapper.eq("state",pageIn.getT().getState());
        }
        queryWrapper.orderByDesc("state", "create_time");
        return postService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), queryWrapper);
    }

    private QueryWrapper<Post> getPostQueryWrapper(PageIn<Post> pageIn) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>().like("title", pageIn.getT().getTitle() == null ? "" : pageIn.getT().getTitle())
                .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName());
        if (!ObjectUtils.isEmpty(pageIn.getT().getUserId())) {
            queryWrapper.eq("user_id", pageIn.getT().getUserId());
        }
        return queryWrapper;
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

    @RequestMapping("postListOrderByRead")
    public Object postListOrderByRead(@RequestBody PageIn<Post> pageIn) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>().like("title", pageIn.getT().getTitle() == null ? "" : pageIn.getT().getTitle())
                .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName()).orderByDesc("read_count");
        if (!ObjectUtils.isEmpty(pageIn.getT().getUserId())) {
            queryWrapper.eq("user_id", pageIn.getT().getUserId());
        }
        queryWrapper.eq("state", 1);
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

    @RequestMapping("freshPost")
    public Object freshPost(@RequestBody Post post) {
        if (post.getUserId() != null) {
            postService.update(post, new UpdateWrapper<Post>().eq("user_id", post.getUserId()));
        }
        return true;
    }

}
