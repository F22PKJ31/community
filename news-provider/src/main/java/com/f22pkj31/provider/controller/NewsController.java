package com.f22pkj31.provider.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.HeadImg;
import com.f22pkj31.community.entity.News;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.provider.service.IHeadImgService;
import com.f22pkj31.provider.service.INewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Slf4j
public class NewsController {

    @Autowired
    private INewsService newsService;

    @Autowired
    private IHeadImgService headImgService;

    @Value("${resources_path}")
    private String filePath;

    @RequestMapping("newsList")
    public Object newsList(@RequestBody PageIn<News> pageIn) {
        QueryWrapper<News> queryWrapper = getNewsQueryWrapper(pageIn);
        return newsService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), queryWrapper.eq("state", 1).orderByDesc("create_time"));
    }

    @RequestMapping("allNewsList")
    public Object allNewsList(@RequestBody PageIn<News> pageIn) {
        QueryWrapper<News> queryWrapper = getNewsQueryWrapper(pageIn);
        if (!StringUtils.isEmpty(pageIn.getT().getState())) {
            queryWrapper.eq("state", pageIn.getT().getState());
        }
        return newsService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), queryWrapper.orderByDesc("state", "create_time"));
    }

    @RequestMapping("sendNews")
    public Object sendNews(@RequestBody News news, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        upLoadFile(news, file);
        return newsService.saveOrUpdate(news);
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
    public Object updateNews(@RequestBody News news, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        upLoadFile(news, file);
        return newsService.saveOrUpdate(news);
    }

    @RequestMapping("newsListOrderByRead")
    public Object newsListOrderByRead(@RequestBody PageIn<News> pageIn) {
        QueryWrapper<News> queryWrapper = getNewsQueryWrapper(pageIn);
        return newsService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                queryWrapper.eq("state", 1).orderByDesc("read_count"));
    }

    @RequestMapping("addReadCount")
    public void addReadCount(@RequestBody CommonId commonId) {
        News news = newsService.getById(commonId.getId());
        news.setReadCount(news.getReadCount() + 1);
        newsService.updateById(news);
    }

    @RequestMapping("subReadCount")
    public void subReadCount(@RequestBody CommonId commonId) {
        News news = newsService.getById(commonId.getId());
        news.setReadCount(news.getReadCount() - 1);
        newsService.updateById(news);
    }


    @RequestMapping("sendHeadImg")
    public Object sendHeadImg(@RequestBody HeadImg headImg) {
        return headImgService.saveOrUpdate(headImg);
    }

    @RequestMapping("headImgList")
    public List<Map<String, Object>> headImgList() {

        List<Map<String, Object>> objects = new ArrayList<>();
        List<HeadImg> list = headImgService.list();
        for (HeadImg headImg : list) {
            Map<String, Object> map = new HashMap<>();
            News news = this.newsDetail(new CommonId().setId(headImg.getNewsId()));
            map.put("headId", headImg.getHeadId());
            map.put("imgUrl", headImg.getImgUrl());
            map.put("newsId", news.getNewsId());
            map.put("title", news.getTitle());
            objects.add(map);
        }
        return objects;
    }

    @RequestMapping("deleteHeadImg")
    public Object deleteHeadImg(@RequestBody CommonId commonId) {
        return headImgService.removeById(commonId.getId());
    }


    @RequestMapping("freshNews")
    public Object freshNews(@RequestBody News news) {
        if (news.getUserId() != null) {
            newsService.update(news, new UpdateWrapper<News>().eq("user_id", news.getUserId()));
        }
        if (news.getCategoryId() != null) {
            newsService.update(news, new UpdateWrapper<News>().eq("category_id", news.getCategoryId()));
        }
        return true;
    }

    private QueryWrapper<News> getNewsQueryWrapper(@RequestBody PageIn<News> pageIn) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<News>().like("title", pageIn.getT().getTitle() == null ? "" : pageIn.getT().getTitle())
                .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName());
        if (!ObjectUtils.isEmpty(pageIn.getT().getCategoryId())) {
            queryWrapper.eq("category_id", pageIn.getT().getCategoryId());
        }
        return queryWrapper;
    }

    private void upLoadFile(@RequestBody News news, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        if (file != null) {
            String name = file.getOriginalFilename();
            if (!new File(filePath + name).exists()) {
                assert name != null;
                name = System.currentTimeMillis() + name.substring(name.lastIndexOf("."));
                log.debug("name={}", name);
                File newFile = new File(filePath + name);
                file.transferTo(newFile);
            }
            news.setImgUrl("http://127.0.0.1:8010/" + name);
        }
    }
}
