package com.f22pkj31.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.NewsCollection;
import com.f22pkj31.community.entity.PageIn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "news-provider")
@RequestMapping("/provider/news")
public interface NewsCollectionClientService {

    @RequestMapping("freshCollection")
    public Object freshCollection(@RequestBody NewsCollection newsCollection);

    @RequestMapping("saveCollection")
    public Object sendCollection(@RequestBody NewsCollection newsCollection);

    @RequestMapping("collectionList")
    IPage<NewsCollection> collectionList(@RequestBody PageIn<NewsCollection> pageIn);

    @RequestMapping("deleteCollection")
    Object deleteCollection(@RequestBody CommonId commonId);

    @RequestMapping("collectionListByUserId")
    Object collectionListByUserId(@RequestBody PageIn<NewsCollection> pageIn);
}
