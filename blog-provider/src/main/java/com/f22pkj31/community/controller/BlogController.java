package com.f22pkj31.community.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.*;
import com.f22pkj31.community.service.IBlogCollectionService;
import com.f22pkj31.community.service.IBlogCommentService;
import com.f22pkj31.community.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
        if (pageIn.getT() == null) {
            return blogService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), null);
        }
        return blogService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), new QueryWrapper<>(pageIn.getT()));
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
        return blogService.updateById(blog);
    }

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody BlogComment blogComment) {
        return blogCommentService.save(blogComment);
    }

    @RequestMapping("commentList")
    public IPage<BlogComment> commentList(@RequestBody PageIn<BlogComment> pageIn) {
        return blogCommentService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                new QueryWrapper<BlogComment>().like("blog_title", pageIn.getT().getBlogTitle() == null ? "" : pageIn.getT().getBlogTitle())
                        .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName()));
    }

    @RequestMapping("deleteComment")
    public Object deleteComment(@RequestBody CommonId commonId) {
        return blogCommentService.removeById(commonId.getId());
    }

    @RequestMapping("sendCollection")
    public Object sendCollection(@RequestBody BlogCollection blogCollection) {
        return blogCollectionService.save(blogCollection);
    }

    @RequestMapping("collectionList")
    public IPage<BlogCollection> collectionList(@RequestBody PageIn<BlogCollection> pageIn) {
        return blogCollectionService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                new QueryWrapper<BlogCollection>().like("blog_title", pageIn.getT().getBlogTitle() == null ? "" : pageIn.getT().getBlogTitle())
                        .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName()));
    }

    @RequestMapping("deleteCollection")
    public Object deleteCollection(@RequestBody CommonId commonId) {
        return blogCollectionService.removeById(commonId.getId());
    }


}
