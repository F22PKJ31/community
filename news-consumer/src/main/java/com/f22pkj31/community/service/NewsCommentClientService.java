package com.f22pkj31.community.service;

import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.NewsComment;
import com.f22pkj31.community.entity.PageIn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "news-provider")
@RequestMapping("/provider/news")
public interface NewsCommentClientService {

    @RequestMapping("sendComment")
    Object sendComment(@RequestBody NewsComment newsComment);

    @RequestMapping("commentList")
    Object commentList(@RequestBody PageIn<NewsComment> pageIn);

    @RequestMapping("deleteComment")
    Object deleteComment(@RequestBody CommonId commonId);

    @RequestMapping("countComment")
    public int countComment(@RequestBody CommonId commonId);

    @RequestMapping("commentDetail")
    public NewsComment commentDetail(@RequestBody CommonId commonId);

    @RequestMapping("deleteCommentList")
    public Object deleteComment(@RequestBody NewsComment newsComment);

    @RequestMapping("freshComment")
    public boolean freshComment(@RequestBody NewsComment newsComment);
}
