package com.f22pkj31.consumer.controller;


import com.f22pkj31.community.entity.*;
import com.f22pkj31.consumer.service.NewsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping("allNewsList")
    public Object allNewsList(@RequestBody PageIn<News> pageIn) {
        return newsClientService.allNewsList(pageIn);
    }

    @RequestMapping("sendNews")
    public Object sendNews(@RequestBody News news, @RequestParam(value = "file", required = false) MultipartFile file) {
        return newsClientService.sendNews(news.setCreateTime(LocalDateTime.now()), file);
    }

    @RequestMapping("deleteNews")
    public Object deleteNews(@RequestBody CommonId commonId) {
        newsClientService.deleteComment(new NewsComment().setNewsId(commonId.getId()));
        return newsClientService.deleteNews(commonId);
    }

    @RequestMapping("newsDetail")
    public News newsDetail(@RequestBody CommonId commonId) {
        return newsClientService.newsDetail(commonId);
    }

    @RequestMapping("updateNews")
    public Object updateNews(@RequestBody News news, @RequestParam(value = "file", required = false) MultipartFile file) {
        newsClientService.updateNews(news.setCreateTime(LocalDateTime.now()), file);
        newsClientService.freshComment(new NewsComment().setNewsId(news.getNewsId()).setNewsTitle(news.getTitle()));
        newsClientService.freshCollection(new NewsCollection().setNewsId(news.getNewsId()).setNewsTitle(news.getTitle()));
        return true;
    }

    @RequestMapping("newsListOrderByRead")
    public Object newsListOrderByRead(@RequestBody PageIn<News> pageIn) {
        return newsClientService.newsListOrderByRead(pageIn);
    }

    @RequestMapping("addReadCount")
    public void addReadCount(@RequestBody CommonId commonId) {
        newsClientService.addReadCount(commonId);
    }

    @RequestMapping("subReadCount")
    public void subReadCount(@RequestBody CommonId commonId) {
        newsClientService.subReadCount(commonId);
    }

    @RequestMapping("sendHeadImg")
    public Object sendHeadImg(@RequestBody HeadImg headImg) {
        return newsClientService.sendHeadImg(headImg);
    }

    @RequestMapping("headImgList")
    public Object headImgList() {
        return newsClientService.headImgList();
    }

}
