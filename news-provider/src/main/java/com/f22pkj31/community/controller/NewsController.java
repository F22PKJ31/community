package com.f22pkj31.community.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.*;
import com.f22pkj31.community.service.IHeadImgService;
import com.f22pkj31.community.service.INewsCollectionService;
import com.f22pkj31.community.service.INewsCommentService;
import com.f22pkj31.community.service.INewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
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
    private INewsCommentService newsCommentService;

    @Autowired
    private INewsCollectionService newsCollectionService;

    @Autowired
    private IHeadImgService headImgService;

    @Value("${resources_path}")
    private String filePath;

    @RequestMapping("newsList")
    public Object newsList(@RequestBody PageIn<News> pageIn) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<News>().like("title", pageIn.getT().getTitle() == null ? "" : pageIn.getT().getTitle())
                .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName());
        if (!ObjectUtils.isEmpty(pageIn.getT().getCategoryId())) {
            queryWrapper.eq("category_id", pageIn.getT().getCategoryId());
        }
        return newsService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), queryWrapper.orderByDesc("create_time"));
    }

    @RequestMapping("sendNews")
    public Object sendNews(@RequestBody News news, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
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
    public Object updateNews(@RequestBody News news, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
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
        return newsService.updateById(news);
    }

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody NewsComment newsComment) {
        return newsCommentService.save(newsComment);
    }

    @RequestMapping("commentList")
    public IPage<NewsComment> commentList(@RequestBody PageIn<NewsComment> pageIn) {
        if (pageIn.getT().getNewsId() != null) {
            return newsCommentService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                    new QueryWrapper<>(pageIn.getT()).orderByDesc("create_time"));
        }
        return newsCommentService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                new QueryWrapper<NewsComment>().like("news_title", pageIn.getT().getNewsTitle() == null ? "" : pageIn.getT().getNewsTitle())
                        .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName())
                        .orderByDesc("create_time"));
    }

    @RequestMapping("deleteComment")
    public Object deleteComment(@RequestBody CommonId commonId) {
        return newsCommentService.removeById(commonId.getId());
    }

    @RequestMapping("deleteCommentList")
    public Object deleteComment(@RequestBody NewsComment newsComment) {
        return newsCommentService.remove(new UpdateWrapper<>(newsComment));
    }

    @RequestMapping("collectionList")
    public IPage<NewsCollection> collectionList(@RequestBody PageIn<NewsCollection> pageIn) {
        return newsCollectionService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                new QueryWrapper<NewsCollection>().like("news_title", pageIn.getT().getNewsTitle() == null ? "" : pageIn.getT().getNewsTitle())
                        .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName())
                        .orderByDesc("create_time"));
    }

    @RequestMapping("deleteCollection")
    public Object deleteCollection(@RequestBody CommonId commonId) {
        return newsCollectionService.removeById(commonId.getId());
    }

    @RequestMapping("collectionListByUserId")
    public Object collectionListByUserId(@RequestBody PageIn<NewsCollection> pageIn) {
        return newsCollectionService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), new QueryWrapper<>(pageIn.getT()).orderByDesc("create_time"));
    }

    @RequestMapping("saveCollection")
    public Object sendCollection(@RequestBody NewsCollection newsCollection) {
        return newsCollectionService.save(newsCollection);
    }

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId) {
        return newsCommentService.count(new QueryWrapper<NewsComment>().eq("news_id", commonId.getId()));
    }

    @RequestMapping("newsListOrderByRead")
    public Object newsListOrderByRead(@RequestBody PageIn<News> pageIn) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<News>().like("title", pageIn.getT().getTitle() == null ? "" : pageIn.getT().getTitle())
                .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName());
        if (!ObjectUtils.isEmpty(pageIn.getT().getCategoryId())) {
            queryWrapper.eq("category_id", pageIn.getT().getCategoryId());
        }
        return newsService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), queryWrapper.orderByDesc("read_count"));
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

    @RequestMapping("commentDetail")
    public NewsComment commentDetail(@RequestBody CommonId commonId) {
        return newsCommentService.getById(commonId.getId());
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
            newsService.update(news, new UpdateWrapper<News>().eq("userId", news.getUserId()));
        }
        if (news.getCategoryId() != null) {
            newsService.update(news, new UpdateWrapper<News>().eq("categoryId", news.getCategoryId()));
        }
        return true;
    }

    @RequestMapping("freshComment")
    public boolean freshComment(@RequestBody NewsComment newsComment) {
        if (newsComment.getNewsId() != null) {
            newsCommentService.update(newsComment, new UpdateWrapper<NewsComment>().eq("newsId", newsComment.getNewsId()));
        }
        if (newsComment.getCommentId() != null) {
            newsCommentService.update(newsComment, new UpdateWrapper<NewsComment>().eq("userId", newsComment.getUserId()));
        }
        return true;
    }

    @RequestMapping("freshCollection")
    public Object freshCollection(@RequestBody NewsCollection newsCollection) {
        if (newsCollection.getUserId() != null) {
            newsCollectionService.update(newsCollection, new UpdateWrapper<NewsCollection>().eq("userId", newsCollection.getUserId()));
        }
        if (newsCollection.getNewsId() != null) {
            newsCollectionService.update(newsCollection, new UpdateWrapper<NewsCollection>().eq("newsId", newsCollection.getNewsId()));
        }
        return true;
    }
}
