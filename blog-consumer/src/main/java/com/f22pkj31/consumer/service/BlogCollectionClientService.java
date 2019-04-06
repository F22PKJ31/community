package com.f22pkj31.consumer.service;

import com.f22pkj31.community.entity.BlogCollection;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "blog-provider")
@RequestMapping("/provider/blog")
public interface BlogCollectionClientService {

    @RequestMapping("deleteCollect")
    public boolean deleteCollect(@RequestBody CommonId commonId);

    @RequestMapping("sendCollection")
    public Object sendCollection(@RequestBody BlogCollection blogCollection);

    @RequestMapping("collectionList")
    public Object collectionList(@RequestBody PageIn<BlogCollection> pageIn);

    @RequestMapping("deleteCollection")
    public Object deleteCollection(@RequestBody CommonId commonId);

    @RequestMapping("collectionListByUserId")
    public Object collectionListByUserId(@RequestBody PageIn<BlogCollection> pageIn);

    @RequestMapping("freshCollection")
    public Object freshCollection(@RequestBody BlogCollection blogCollection);
}
