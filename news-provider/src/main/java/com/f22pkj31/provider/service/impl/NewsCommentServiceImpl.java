package com.f22pkj31.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f22pkj31.community.entity.NewsComment;
import com.f22pkj31.community.mapper.NewsCommentMapper;
import com.f22pkj31.provider.service.INewsCommentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author f22pkj31
 * @since 2019-03-24
 */
@Service
public class NewsCommentServiceImpl extends ServiceImpl<NewsCommentMapper, NewsComment> implements INewsCommentService {

}
