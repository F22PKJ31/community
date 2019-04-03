package com.f22pkj31.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.f22pkj31.community.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(value = "news-provider")
@RequestMapping("/provider/news")
public interface NewsClientService {

    @RequestMapping("freshNews")
    public Object freshNews(@RequestBody News news);

}