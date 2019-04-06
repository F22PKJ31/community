package com.f22pkj31.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f22pkj31.community.entity.Category;
import com.f22pkj31.community.mapper.CategoryMapper;
import com.f22pkj31.provider.service.ICategoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author f22pkj31
 * @since 2019-03-16
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
