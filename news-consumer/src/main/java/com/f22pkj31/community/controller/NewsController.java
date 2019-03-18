package com.f22pkj31.community.controller;


import com.f22pkj31.community.entity.*;
import com.f22pkj31.community.service.NewsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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
        return newsClientService.sendNews(news.setCreateTime(LocalDateTime.now()));
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
        return newsClientService.updateNews(news.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody NewsComment newsComment) {
        CommonId commonId = new CommonId();
        commonId.setId(newsComment.getNewsId());
        newsClientService.addReadCount(commonId);
        return newsClientService.sendComment(newsComment.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("commentList")
    public Object commentList(@RequestBody PageIn<NewsComment> pageIn) {
        return newsClientService.commentList(pageIn);
    }

    @RequestMapping("deleteComment")
    public Object deleteComment(@RequestBody CommonId commonId) {
        NewsComment newsComment = newsClientService.commentDetail(commonId);
        CommonId id = new CommonId();
        commonId.setId(newsComment.getNewsId());
        newsClientService.subReadCount(id);
        return newsClientService.deleteComment(commonId);
    }

    @RequestMapping("collectionList")
    public Object collectionList(@RequestBody PageIn<NewsCollection> pageIn) {
        return newsClientService.collectionList(pageIn);
    }

    @RequestMapping("deleteCollection")
    public Object deleteCollection(@RequestBody CommonId commonId) {
        return newsClientService.deleteCollection(commonId);
    }

    @RequestMapping("collectionListByUserId")
    public Object collectionListByUserId(@RequestBody PageIn<NewsCollection> pageIn) {
        return newsClientService.collectionListByUserId(pageIn);
    }

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId) {
        return newsClientService.countComment(commonId);
    }

    @RequestMapping("newsListOrderByRead")
    public Object newsListOrderByRead(@RequestBody PageIn<News> pageIn){
        return newsClientService.newsListOrderByRead(pageIn);
    }

    @RequestMapping("addReadCount")
    public void addReadCount(@RequestBody CommonId commonId){
        newsClientService.addReadCount(commonId);
    }

    @RequestMapping("subReadCount")
    public void subReadCount(@RequestBody CommonId commonId){
        newsClientService.subReadCount(commonId);
    }

}
