package com.f22pkj31.community.service;

import com.f22pkj31.community.entity.Category;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(value = "category-provider")
@RequestMapping("/provider/category")
public interface CategoryClientService {

    @RequestMapping("addCategory")
    public boolean addCategory(@RequestBody Category category);

    @RequestMapping("getCategory")
    public Category getCategory(@RequestBody CommonId commonId);

    @RequestMapping("getCategoryByName")
    public Category getCategoryByName(@RequestBody Category category);

    @RequestMapping("deleteCategory")
    public boolean deleteCategory(@RequestBody CommonId commonId);

    @RequestMapping("categoryListByPage")
    public Object categoryList(@RequestBody PageIn<Category> pageIn);

    @RequestMapping("categoryList")
    public Object categoryList();

    @RequestMapping("updateCategory")
    boolean updateCategory(Category category);
}
