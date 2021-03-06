package com.f22pkj31.consumer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.f22pkj31.community.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(value = "news-provider")
@RequestMapping("/provider/news")
public interface NewsClientService {

    @RequestMapping("newsList")
    Object newsList(@RequestBody PageIn<News> pageIn);

    @RequestMapping("allNewsList")
    Object allNewsList(@RequestBody PageIn<News> pageIn);

    @RequestMapping("sendNews")
    Object sendNews(@RequestBody News news, @RequestParam(value = "file", required = false) MultipartFile file);

    @RequestMapping("deleteNews")
    Object deleteNews(@RequestBody CommonId commonId);

    @RequestMapping("newsDetail")
    News newsDetail(@RequestBody CommonId commonId);

    @RequestMapping("updateNews")
    Object updateNews(@RequestBody News news, @RequestParam(value = "file", required = false) MultipartFile file);

    @RequestMapping("newsListOrderByRead")
    Object newsListOrderByRead(@RequestBody PageIn<News> pageIn);

    @RequestMapping("addReadCount")
    void addReadCount(@RequestBody CommonId commonId);

    @RequestMapping("subReadCount")
    void subReadCount(@RequestBody CommonId commonId);

    @RequestMapping("sendHeadImg")
    Object sendHeadImg(@RequestBody HeadImg headImg);

    @RequestMapping("headImgList")
    Object headImgList();

    @RequestMapping("deleteHeadImg")
    Object deleteHeadImg(@RequestBody CommonId commonId);

    @RequestMapping("updateHeadImg")
    Object updateHeadImg(@RequestBody HeadImg headImg, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException;

    @RequestMapping("freshNews")
    Object freshNews(@RequestBody News news);

    @RequestMapping("freshCollection")
    Object freshCollection(@RequestBody NewsCollection newsCollection);

    @RequestMapping("saveCollection")
    Object sendCollection(@RequestBody NewsCollection newsCollection);

    @RequestMapping("collectionList")
    IPage<NewsCollection> collectionList(@RequestBody PageIn<NewsCollection> pageIn);

    @RequestMapping("deleteCollection")
    Object deleteCollection(@RequestBody CommonId commonId);

    @RequestMapping("collectionListByUserId")
    Object collectionListByUserId(@RequestBody PageIn<NewsCollection> pageIn);

    @RequestMapping("sendComment")
    Object sendComment(@RequestBody NewsComment newsComment);

    @RequestMapping("commentList")
    Object commentList(@RequestBody PageIn<NewsComment> pageIn);

    @RequestMapping("deleteComment")
    Object deleteComment(@RequestBody CommonId commonId);

    @RequestMapping("countComment")
    int countComment(@RequestBody CommonId commonId);

    @RequestMapping("commentDetail")
    NewsComment commentDetail(@RequestBody CommonId commonId);

    @RequestMapping("deleteCommentList")
    Object deleteComment(@RequestBody NewsComment newsComment);

    @RequestMapping("freshComment")
    boolean freshComment(@RequestBody NewsComment newsComment);

}