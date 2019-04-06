package com.f22pkj31.consumer.controller;


import com.f22pkj31.community.entity.*;
import com.f22pkj31.consumer.service.BlogClientService;
import com.f22pkj31.consumer.service.NewsClientService;
import com.f22pkj31.consumer.service.PostClientService;
import com.f22pkj31.consumer.service.UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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

    @Autowired
    private BlogClientService blogClientService;

    @Autowired
    private PostClientService postClientService;

    @Autowired
    private NewsClientService newsClientService;

    @RequestMapping("addUser")
    public boolean addUser(@RequestBody User user) {
        return userClientService.addUser(user.setCreateTime(LocalDateTime.now()));
    }

    @RequestMapping("updateUser")
    public boolean updateUser(@RequestBody User user) {
        blogClientService.freshBlog(new Blog().setUserId(user.getUserId()).setUserName(user.getUserName()));
        blogClientService.freshCollection(new BlogCollection().setUserId(user.getUserId()).setUserName(user.getUserName()));
        blogClientService.freshComment(new BlogComment().setUserId(user.getUserId()).setUserName(user.getUserName()));
        postClientService.freshComment(new PostComment().setUserId(user.getUserId()).setUserName(user.getUserName()));
        postClientService.freshPost(new Post().setUserId(user.getUserId()).setUserName(user.getUserName()));
        postClientService.freshCollection(new PostCollection().setUserId(user.getUserId()).setUserName(user.getUserName()));
        newsClientService.freshCollection(new NewsCollection().setUserId(user.getUserId()).setUserName(user.getUserName()));
        newsClientService.freshComment(new NewsComment().setUserId(user.getUserId()).setUserName(user.getUserName()));
        newsClientService.freshNews(new News().setUserId(user.getUserId()).setUserName(user.getUserName()));
         userClientService.updateUser(user.setCreateTime(LocalDateTime.now()));
        return true;
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
