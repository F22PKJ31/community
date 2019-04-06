package com.f22pkj31.community.service;

import com.f22pkj31.community.entity.BlogComment;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "blog-provider")
@RequestMapping("/provider/blog")
public interface BlogCommentClientService {

    @RequestMapping("sendComment")
    Object sendComment(@RequestBody BlogComment blogComment);

    @RequestMapping("commentList")
    Object commentList(@RequestBody PageIn<BlogComment> pageIn);

    @RequestMapping("deleteComment")
    Object deleteComment(@RequestBody CommonId commonId);

    @RequestMapping("countComment")
    int countComment(@RequestBody CommonId commonId);

    @RequestMapping("freshComment")
    boolean freshComment(@RequestBody BlogComment blogComment);

    @RequestMapping("commentDetail")
    BlogComment commentDetail(@RequestBody CommonId commonId);

    @RequestMapping("deleteCommentList")
    Object deleteComment(@RequestBody BlogComment blogComment);
}
