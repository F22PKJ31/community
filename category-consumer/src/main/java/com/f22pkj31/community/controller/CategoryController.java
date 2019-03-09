package com.f22pkj31.community.controller;


import com.f22pkj31.community.entity.Category;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.service.CategoryClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author f22pkj31
 * @since 2019-02-23
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryClientService categoryClientService;

    @RequestMapping("addCategory")
    public boolean addCategory(@RequestBody Category category) {
        return categoryClientService.addCategory(category);
    }

    @RequestMapping("getCategory")
    public Category getCategory(@RequestBody CommonId commonId) {
        return categoryClientService.getCategory(commonId);
    }

    @RequestMapping("getCategoryByName")
    public Category getCategoryByName(@RequestBody Category category) {
        return categoryClientService.getCategoryByName(category);
    }

    @RequestMapping("deleteCategory")
    public boolean deleteCategory(@RequestBody CommonId commonId) {
        return categoryClientService.deleteCategory(commonId);
    }

    @RequestMapping("categoryList")
    public Object categoryList(@RequestBody PageIn<Category> pageIn) {
        return categoryClientService.categoryList(pageIn);
    }

}
