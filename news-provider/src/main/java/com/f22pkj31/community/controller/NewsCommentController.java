package com.f22pkj31.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.NewsComment;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.service.INewsCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider/news")
@Slf4j
public class NewsCommentController {

    @Autowired
    private INewsCommentService newsCommentService;

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

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId) {
        return newsCommentService.count(new QueryWrapper<NewsComment>().eq("news_id", commonId.getId()));
    }

    @RequestMapping("commentDetail")
    public NewsComment commentDetail(@RequestBody CommonId commonId) {
        return newsCommentService.getById(commonId.getId());
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

}
