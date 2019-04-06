package com.f22pkj31.consumer.service;

import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.HeadImg;
import com.f22pkj31.community.entity.News;
import com.f22pkj31.community.entity.PageIn;
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

    @RequestMapping("newsListOrderByRead")
    public Object newsListOrderByRead(@RequestBody PageIn<News> pageIn);

    @RequestMapping("addReadCount")
    public void addReadCount(@RequestBody CommonId commonId);

    @RequestMapping("subReadCount")
    public void subReadCount(@RequestBody CommonId commonId);

    @RequestMapping("sendHeadImg")
    public Object sendHeadImg(@RequestBody HeadImg headImg);

    @RequestMapping("headImgList")
    public Object headImgList();

    @RequestMapping("deleteHeadImg")
    public Object deleteHeadImg(@RequestBody CommonId commonId);

    @RequestMapping("updateHeadImg")
    public Object updateHeadImg(@RequestBody HeadImg headImg, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException;

    @RequestMapping("freshNews")
    public Object freshNews(@RequestBody News news);

}