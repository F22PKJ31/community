package com.f22pkj31.community.service;

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

    @RequestMapping("sendNews")
    Object sendNews(@RequestBody News news, @RequestParam(value = "file", required = false) MultipartFile file);

    @RequestMapping("deleteNews")
    Object deleteNews(@RequestBody CommonId commonId);

    @RequestMapping("newsDetail")
    News newsDetail(@RequestBody CommonId commonId);

    @RequestMapping("updateNews")
    Object updateNews(@RequestBody News news, @RequestParam(value = "file", required = false) MultipartFile file);

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

    @RequestMapping("newsListOrderByRead")
    public Object newsListOrderByRead(@RequestBody PageIn<News> pageIn);

    @RequestMapping("addReadCount")
    public void addReadCount(@RequestBody CommonId commonId);

    @RequestMapping("subReadCount")
    public void subReadCount(@RequestBody CommonId commonId);


    @RequestMapping("commentDetail")
    public NewsComment commentDetail(@RequestBody CommonId commonId);

    @RequestMapping("sendHeadImg")
    public Object sendHeadImg(@RequestBody HeadImg headImg, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException;

    @RequestMapping("headImgList")
    public Object headImgList();

    @RequestMapping("deleteHeadImg")
    public Object deleteHeadImg(@RequestBody CommonId commonId);

    @RequestMapping("updateHeadImg")
    public Object updateHeadImg(@RequestBody HeadImg headImg, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException;

    @RequestMapping("saveCollection")
    public Object sendCollection(@RequestBody NewsCollection newsCollection);
}