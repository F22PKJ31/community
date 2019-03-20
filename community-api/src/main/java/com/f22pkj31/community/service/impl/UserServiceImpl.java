package com.f22pkj31.community.service.impl;

import com.f22pkj31.community.entity.User;
import com.f22pkj31.community.mapper.UserMapper;
import com.f22pkj31.community.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author f22pkj31
 * @since 2019-03-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
