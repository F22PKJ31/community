package com.f22pkj31.consumer.service;

import com.f22pkj31.community.entity.News;
import com.f22pkj31.community.entity.NewsCollection;
import com.f22pkj31.community.entity.NewsComment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "news-provider")
@RequestMapping("/provider/news")
public interface NewsClientService {

    @RequestMapping("freshNews")
    public Object freshNews(@RequestBody News news);

    @RequestMapping("freshComment")
    public boolean freshComment(@RequestBody NewsComment newsComment);

    @RequestMapping("freshCollection")
    public Object freshCollection(@RequestBody NewsCollection newsCollection);

}