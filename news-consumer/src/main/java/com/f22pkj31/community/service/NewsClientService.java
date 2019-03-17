package com.f22pkj31.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.f22pkj31.community.entity.*;
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

    @RequestMapping("collectionList")
    IPage<NewsCollection> collectionList(@RequestBody PageIn<NewsCollection> pageIn);

    @RequestMapping("deleteCollection")
    Object deleteCollection(@RequestBody CommonId commonId);

    @RequestMapping("collectionListByUserId")
    Object collectionListByUserId(@RequestBody PageIn<NewsCollection> pageIn);

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId);

}
