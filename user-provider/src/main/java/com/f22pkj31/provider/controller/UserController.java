package com.f22pkj31.provider.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.entity.User;
import com.f22pkj31.provider.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author f22pkj31
 * @since 2019-02-23
 */
@RestController
@RequestMapping("/provider/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("addUser")
    public User addUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @RequestMapping("updateUser")
    public boolean updateUser(@RequestBody User user) {
        return userService.updateById(user);
    }

    @RequestMapping("getUser")
    public User getUser(@RequestBody CommonId commonId) {
        return userService.getById(commonId.getId());
    }

    @RequestMapping("getUserByName")
    public User getUserByName(@RequestBody User user) {
        return userService.getOne(new QueryWrapper<>(user));
    }

    @RequestMapping("deleteUser")
    public boolean deleteUser(@RequestBody CommonId commonId) {
        return userService.removeById(commonId.getId());
    }

    @RequestMapping("userList")
    public IPage<User> userList(@RequestBody PageIn<User> pageIn) {
        return userService.page(new Page<>(pageIn.getCurrent(), pageIn.getSize()), new QueryWrapper<User>()
                .like("user_name", pageIn.getT().getUserName() == null ? "" : pageIn.getT().getUserName()).orderByDesc("create_time"));
    }

}
