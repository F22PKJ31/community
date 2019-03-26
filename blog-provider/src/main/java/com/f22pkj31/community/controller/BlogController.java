package com.f22pkj31.community.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.*;
import com.f22pkj31.community.service.IBlogCollectionService;
import com.f22pkj31.community.service.IBlogCommentService;
import com.f22pkj31.community.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @user f22pkj31
 * @since 2019-02-23
 */
@RestController
@CrossOrigin
@RequestMapping("/provider/blog")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private IBlogCommentService blogCommentService;

    @Autowired
    private IBlogCollectionService blogCollectionService;

    @RequestMapping("blogList")
    public Object blogList(@RequestBody PageIn<Blog> pageIn) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<Blog>().like("title", pageIn.getT().getTitle() == null ? "" : pageIn.getT().getTitle())
                .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName());
        if (!ObjectUtils.isEmpty(pageIn.getT().getCategoryId())) {
            queryWrapper.eq("category_id", pageIn.getT().getCategoryId());
        }
        if (!ObjectUtils.isEmpty(pageIn.getT().getUserId())) {
            queryWrapper.eq("user_id", pageIn.getT().getUserId());
        }
        return blogService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), queryWrapper.orderByDesc("create_time"));

    }

    @RequestMapping("sendBlog")
    public Object sendBlog(@RequestBody Blog blog) {
        return blogService.save(blog);
    }

    @RequestMapping("deleteBlog")
    public Object deleteBlog(@RequestBody CommonId commonId) {
        return blogService.removeById(commonId.getId());
    }

    @RequestMapping("blogDetail")
    public Blog blogDetail(@RequestBody CommonId commonId) {
        return blogService.getById(commonId.getId());
    }

    @RequestMapping("updateBlog")
    public Object updateBlog(@RequestBody Blog blog) {
        return blogService.saveOrUpdate(blog);
    }

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

    @RequestMapping("sendCollection")
    public Object sendCollection(@RequestBody BlogCollection blogCollection) {
        return blogCollectionService.save(blogCollection);
    }

    @RequestMapping("collectionList")
    public IPage<BlogCollection> collectionList(@RequestBody PageIn<BlogCollection> pageIn) {
        return blogCollectionService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                new QueryWrapper<BlogCollection>().like("blog_title", pageIn.getT().getBlogTitle() == null ? "" : pageIn.getT().getBlogTitle())
                        .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName())
                        .orderByDesc("create_time"));
    }

    @RequestMapping("deleteCollection")
    public Object deleteCollection(@RequestBody CommonId commonId) {
        return blogCollectionService.removeById(commonId.getId());
    }

    @RequestMapping("collectionListByUserId")
    public Object collectionListByUserId(@RequestBody PageIn<BlogCollection> pageIn) {
        return blogCollectionService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), new QueryWrapper<>(pageIn.getT()));
    }


    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId) {
        return blogCommentService.count(new QueryWrapper<BlogComment>().eq("blog_id", commonId.getId()));
    }

    @RequestMapping("blogListOrderByRead")
    public Object blogListOrderByRead(@RequestBody PageIn<Blog> pageIn) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<Blog>().like("title", pageIn.getT().getTitle() == null ? "" : pageIn.getT().getTitle())
                .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName());
        if (!ObjectUtils.isEmpty(pageIn.getT().getCategoryId())) {
            queryWrapper.eq("category_id", pageIn.getT().getCategoryId());
        }
        if (!ObjectUtils.isEmpty(pageIn.getT().getUserId())) {
            queryWrapper.eq("user_id", pageIn.getT().getUserId());
        }
        return blogService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), queryWrapper.orderByDesc("read_count"));

    }

    @RequestMapping("addReadCount")
    public void addReadCount(@RequestBody CommonId commonId) {
        Blog blog = blogService.getById(commonId.getId());
        blog.setReadCount(blog.getReadCount() + 1);
        blogService.updateById(blog);
    }

    @RequestMapping("subReadCount")
    public void subReadCount(@RequestBody CommonId commonId) {
        Blog blog = blogService.getById(commonId.getId());
        blog.setReadCount(blog.getReadCount() - 1);
        blogService.updateById(blog);
    }

    @RequestMapping("commentDetail")
    public BlogComment commentDetail(@RequestBody CommonId commonId) {
        return blogCommentService.getById(commonId.getId());
    }

}
