package com.f22pkj31.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f22pkj31.community.entity.Post;
import com.f22pkj31.community.mapper.PostMapper;
import com.f22pkj31.provider.service.IPostService;
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
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

}
