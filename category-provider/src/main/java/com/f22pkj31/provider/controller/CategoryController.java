package com.f22pkj31.provider.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.provider.service.ICategoryService;
import com.f22pkj31.community.entity.Category;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("addCategory")
    public boolean addCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @RequestMapping("updateCategory")
    public boolean updateCategory(@RequestBody Category category) {
        return categoryService.updateById(category);
    }

    @RequestMapping("getCategory")
    public Category getCategory(@RequestBody CommonId commonId) {
        return categoryService.getById(commonId.getId());
    }

    @RequestMapping("getCategoryByName")
    public Category getCategoryByName(@RequestBody Category category) {
        return categoryService.getOne(new QueryWrapper<>(category).orderByDesc("create_time"));
    }

    @RequestMapping("deleteCategory")
    public boolean deleteCategory(@RequestBody CommonId commonId) {
        return categoryService.removeById(commonId.getId());
    }

    @RequestMapping("categoryListByPage")
    public Object categoryList(@RequestBody PageIn<Category> pageIn) {
        IPage<Category> page = categoryService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()),
                new QueryWrapper<Category>().like("category_name", pageIn.getT().getCategoryName() == null ? "" : pageIn.getT().getCategoryName())
                        .orderByDesc("create_time"));
        return page;
    }

    @RequestMapping("categoryList")
    public Object categoryList() {
        return categoryService.list();
    }

}
