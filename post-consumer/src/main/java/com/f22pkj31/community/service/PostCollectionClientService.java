package com.f22pkj31.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.entity.PostCollection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "post-provider")
@RequestMapping("/provider/post")
public interface PostCollectionClientService {

    @RequestMapping("collectionList")
    IPage<PostCollection> collectionList(@RequestBody PageIn<PostCollection> pageIn);

    @RequestMapping("deleteCollection")
    Object deleteCollection(@RequestBody CommonId commonId);

    @RequestMapping("collectionListByUserId")
    Object collectionListByUserId(@RequestBody PageIn<PostCollection> pageIn);

    @RequestMapping("saveCollection")
    public Object sendCollection(@RequestBody PostCollection postCollection);

    @RequestMapping("freshCollection")
    public Object freshCollection(@RequestBody PostCollection postCollection);
}
