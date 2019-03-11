package com.f22pkj31.community.controller;


import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.entity.User;
import com.f22pkj31.community.service.UserClientService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserClientService userClientService;

    @RequestMapping("addUser")
    public boolean addUser(@RequestBody User user) {
        return userClientService.addUser(user);
    }

    @RequestMapping("updateUser")
    public boolean updateUser(@RequestBody User user) {
        return userClientService.updateUser(user);
    }


    @RequestMapping("getUser")
    public User getUser(@RequestBody CommonId commonId) {
        return userClientService.getUser(commonId);
    }

    @RequestMapping("getUserByName")
    public User getUserByName(@RequestBody User user) {
        return userClientService.getUserByName(user);
    }

    @RequestMapping("deleteUser")
    public boolean deleteUser(@RequestBody CommonId commonId) {
        return userClientService.deleteUser(commonId);
    }

    @RequestMapping("userList")
    public Object userList(@RequestBody PageIn<User> pageIn) {
        return userClientService.userList(pageIn);
    }

}
