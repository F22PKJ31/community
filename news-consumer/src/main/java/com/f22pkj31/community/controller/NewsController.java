package com.f22pkj31.community.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.News;
import com.f22pkj31.community.entity.NewsComment;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.service.NewsClientService;
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
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsClientService newsClientService;

    @RequestMapping("newsList")
    public Object newsList(@RequestBody PageIn<News> pageIn) {
        return newsClientService.newsList(pageIn);
    }

    @RequestMapping("sendNews")
    public Object sendNews(@RequestBody News news) {
        return newsClientService.sendNews(news);
    }

    @RequestMapping("deleteNews")
    public Object deleteNews(@RequestBody CommonId commonId) {
        return newsClientService.deleteNews(commonId);
    }

    @RequestMapping("newsDetail")
    public News newsDetail(@RequestBody CommonId commonId) {
        return newsClientService.newsDetail(commonId);
    }

    @RequestMapping("updateNews")
    public Object updateNews(@RequestBody News news) {
        return newsClientService.updateNews(news);
    }

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody NewsComment newsComment) {
        return newsClientService.sendComment(newsComment);
    }

    @RequestMapping("commentList")
    public IPage<NewsComment> commentList(@RequestBody PageIn<NewsComment> pageIn) {
        return newsClientService.commentList(pageIn);
    }

    @RequestMapping("deleteComment")
    public Object deleteComment(@RequestBody CommonId commonId) {
        return newsClientService.deleteComment(commonId);
    }


    @RequestMapping("deleteCollect")
    public boolean deleteCollect(@RequestBody CommonId commonId) {
        return newsClientService.deleteCollect(commonId);
    }

}
