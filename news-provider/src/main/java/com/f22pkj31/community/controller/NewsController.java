package com.f22pkj31.community.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.News;
import com.f22pkj31.community.entity.NewsComment;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.service.INewsCommentService;
import com.f22pkj31.community.service.INewsService;
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
@RequestMapping("/provider/news")
public class NewsController {

    @Autowired
    private INewsService newsService;

    @Autowired
    private INewsCommentService newsCommentService;

    @RequestMapping("newsList")
    public Object newsList(@RequestBody PageIn<News> pageIn) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<News>().like("title", pageIn.getT().getTitle() == null ? "" : pageIn.getT().getTitle())
                .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName());
        if (!ObjectUtils.isEmpty(pageIn.getT().getCategoryId())) {
            queryWrapper.eq("category_id", pageIn.getT().getCategoryId());
        }
        return newsService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), queryWrapper);
    }

    @RequestMapping("sendNews")
    public Object sendNews(@RequestBody News news) {
        return newsService.save(news);
    }

    @RequestMapping("deleteNews")
    public Object deleteNews(@RequestBody CommonId commonId) {
        return newsService.removeById(commonId.getId());
    }

    @RequestMapping("newsDetail")
    public News newsDetail(@RequestBody CommonId commonId) {
        return newsService.getById(commonId.getId());
    }

    @RequestMapping("updateNews")
    public Object updateNews(@RequestBody News news) {
        return newsService.updateById(news);
    }

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody NewsComment newsComment) {
        return newsCommentService.save(newsComment);
    }

    @RequestMapping("commentList")
    public IPage<NewsComment> commentList(@RequestBody PageIn<NewsComment> pageIn) {
        return newsCommentService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                new QueryWrapper<NewsComment>().like("news_title", pageIn.getT().getNewsTitle() == null ? "" : pageIn.getT().getNewsTitle())
                        .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName()));
    }

    @RequestMapping("deleteComment")
    public Object deleteComment(@RequestBody CommonId commonId) {
        return newsCommentService.removeById(commonId.getId());
    }

}
