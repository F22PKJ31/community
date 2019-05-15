package com.f22pkj31.consumer.controller;


import com.f22pkj31.community.entity.*;
import com.f22pkj31.consumer.service.BlogClientService;
import com.f22pkj31.consumer.service.CategoryClientService;
import com.f22pkj31.consumer.service.NewsClientService;
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

    @Autowired
    private BlogClientService blogClientService;

    @Autowired
    private NewsClientService newsClientService;


    @RequestMapping("addCategory")
    public boolean addCategory(@RequestBody Category category) {
        return categoryClientService.addCategory(category);
    }

    @RequestMapping("updateCategory")
    public boolean updateCategory(@RequestBody Category category) {
        categoryClientService.updateCategory(category);
        newsClientService.freshNews(new News().setCategoryId(category.getCategoryId()).setCategoryName(category.getCategoryName()));
        blogClientService.freshBlog(new Blog().setCategoryId(category.getCategoryId()).setCategoryName(category.getCategoryName()));
        return true;
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

    @RequestMapping("categoryListByPage")
    public Object categoryList(@RequestBody PageIn<Category> pageIn) {
        return categoryClientService.categoryList(pageIn);
    }

    @RequestMapping("categoryList")
    public Object categoryList() {
        return categoryClientService.categoryList();
    }

}
