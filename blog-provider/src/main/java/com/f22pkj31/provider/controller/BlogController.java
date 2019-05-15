package com.f22pkj31.provider.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.Blog;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.provider.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
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

    @RequestMapping("blogList")
    public Object blogList(@RequestBody PageIn<Blog> pageIn) {
        QueryWrapper<Blog> queryWrapper = getBlogQuery(pageIn);
        return blogService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                queryWrapper.eq("state", 1).orderByDesc("create_time"));

    }

    @RequestMapping("allBlogList")
    public Object allBlogList(@RequestBody PageIn<Blog> pageIn) {
        QueryWrapper<Blog> queryWrapper = getBlogQuery(pageIn);
        if (!StringUtils.isEmpty(pageIn.getT().getState())) {
            queryWrapper.eq("state", pageIn.getT().getState());
        }
        return blogService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                queryWrapper.orderByDesc("state", "create_time"));

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


    @RequestMapping("blogListOrderByRead")
    public Object blogListOrderByRead(@RequestBody PageIn<Blog> pageIn) {
        QueryWrapper<Blog> queryWrapper = getBlogQuery(pageIn);
        return blogService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                queryWrapper.eq("state", 1).orderByDesc("read_count"));

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


    private QueryWrapper<Blog> getBlogQuery(@RequestBody PageIn<Blog> pageIn) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<Blog>().like("title", pageIn.getT().getTitle() == null ? "" : pageIn.getT().getTitle())
                .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName());
        if (!ObjectUtils.isEmpty(pageIn.getT().getCategoryId())) {
            queryWrapper.eq("category_id", pageIn.getT().getCategoryId());
        }
        if (!ObjectUtils.isEmpty(pageIn.getT().getUserId())) {
            queryWrapper.eq("user_id", pageIn.getT().getUserId());
        }
        return queryWrapper;
    }

    @RequestMapping("freshBlog")
    public Object freshBlog(@RequestBody Blog blog) {
        if (blog.getUserId() != null) {
            blogService.update(blog, new UpdateWrapper<Blog>().eq("user_id", blog.getUserId()));
        }
        if (blog.getCategoryId() != null) {
            blogService.update(blog, new UpdateWrapper<Blog>().eq("categoryId", blog.getCategoryId()));
        }
        return true;
    }

}
