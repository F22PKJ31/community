package com.f22pkj31.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f22pkj31.community.entity.PostComment;
import com.f22pkj31.community.mapper.PostCommentMapper;
import com.f22pkj31.community.service.IPostCommentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author f22pkj31
 * @since 2019-03-09
 */
@Service
public class PostCommentServiceImpl extends ServiceImpl<PostCommentMapper, PostComment> implements IPostCommentService {

}
