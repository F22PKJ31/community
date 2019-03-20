package com.f22pkj31.community.service.impl;

import com.f22pkj31.community.entity.Post;
import com.f22pkj31.community.mapper.PostMapper;
import com.f22pkj31.community.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author f22pkj31
 * @since 2019-03-20
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

}
