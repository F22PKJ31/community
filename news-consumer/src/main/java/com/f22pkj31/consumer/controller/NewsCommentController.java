package com.f22pkj31.consumer.controller;

import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.NewsComment;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.consumer.service.NewsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/news")
public class NewsCommentController {

    @Autowired
    private NewsClientService newsClientService;

    @RequestMapping("sendComment")
    public Object sendComment(@RequestBody NewsComment newsComment) {
        CommonId commonId = new CommonId();
        commonId.setId(newsComment.getNewsId());
        newsClientService.addReadCount(commonId);
        return newsClientService.sendComment(newsComment.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("commentList")
    public Object commentList(@RequestBody PageIn<NewsComment> pageIn) {
        return newsClientService.commentList(pageIn);
    }

    @RequestMapping("deleteComment")
    public Object deleteComment(@RequestBody CommonId commonId) {
        NewsComment newsComment = newsClientService.commentDetail(commonId);
        CommonId id = new CommonId();
        id.setId(newsComment.getNewsId());
        newsClientService.subReadCount(id);
        return newsClientService.deleteComment(commonId);
    }

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId) {
        return newsClientService.countComment(commonId);
    }

    @RequestMapping("deleteHeadImg")
    public Object deleteHeadImg(@RequestBody CommonId commonId) {
        return newsClientService.deleteComment(commonId);
    }


}
