package com.f22pkj31.community.service;

import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.News;
import com.f22pkj31.community.entity.NewsComment;
import com.f22pkj31.community.entity.PageIn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "news-provider")
@RequestMapping("/provider/news")
public interface NewsClientService {

    @RequestMapping("newsList")
    Object newsList(@RequestBody PageIn<News> pageIn);

    @RequestMapping("sendNews")
    Object sendNews(@RequestBody News news);

    @RequestMapping("deleteNews")
    Object deleteNews(@RequestBody CommonId commonId);

    @RequestMapping("newsDetail")
    News newsDetail(@RequestBody CommonId commonId);

    @RequestMapping("updateNews")
    Object updateNews(@RequestBody News news);

    @RequestMapping("sendComment")
    Object sendComment(@RequestBody NewsComment newsComment);

    @RequestMapping("commentList")
    Object commentList(@RequestBody PageIn<NewsComment> pageIn);

    @RequestMapping("deleteComment")
    Object deleteComment(@RequestBody CommonId commonId);

    @RequestMapping("deleteCollect")
    public boolean deleteCollect(@RequestBody CommonId commonId);
}
