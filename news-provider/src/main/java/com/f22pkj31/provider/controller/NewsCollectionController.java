package com.f22pkj31.provider.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.NewsCollection;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.provider.service.INewsCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider/news")
@Slf4j
public class NewsCollectionController {

    @Autowired
    private INewsCollectionService newsCollectionService;

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
