package com.f22pkj31.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.BlogCollection;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.service.IBlogCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/provider/blog")
public class BlogCollectionController {

    @Autowired
    private IBlogCollectionService blogCollectionService;

    @RequestMapping("sendCollection")
    public Object sendCollection(@RequestBody BlogCollection blogCollection) {
        return blogCollectionService.save(blogCollection);
    }

    @RequestMapping("collectionList")
    public IPage<BlogCollection> collectionList(@RequestBody PageIn<BlogCollection> pageIn) {
        return blogCollectionService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                new QueryWrapper<BlogCollection>().like("blog_title", pageIn.getT().getBlogTitle() == null ? "" : pageIn.getT().getBlogTitle())
                        .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName())
                        .orderByDesc("create_time"));
    }

    @RequestMapping("deleteCollection")
    public Object deleteCollection(@RequestBody CommonId commonId) {
        return blogCollectionService.removeById(commonId.getId());
    }

    @RequestMapping("collectionListByUserId")
    public Object collectionListByUserId(@RequestBody PageIn<BlogCollection> pageIn) {
        return blogCollectionService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), new QueryWrapper<>(pageIn.getT()));
    }

    @RequestMapping("freshCollection")
    public Object freshCollection(@RequestBody BlogCollection blogCollection) {
        if (blogCollection.getUserId() != null) {
            blogCollectionService.update(blogCollection, new UpdateWrapper<BlogCollection>().eq("userId", blogCollection.getUserId()));
        }
        if (blogCollection.getBlogId() != null) {
            blogCollectionService.update(blogCollection, new UpdateWrapper<BlogCollection>().eq("blogId", blogCollection.getBlogId()));
        }
        return true;
    }
}
