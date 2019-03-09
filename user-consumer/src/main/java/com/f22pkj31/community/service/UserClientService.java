package com.f22pkj31.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.f22pkj31.community.entity.CommonId;
import com.f22pkj31.community.entity.PageIn;
import com.f22pkj31.community.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "post-provider")
@RequestMapping("/provider/user")
public interface UserClientService {

    @RequestMapping("addUser")
    boolean addUser(@RequestBody User user);

    @RequestMapping("getUser")
    User getUser(@RequestBody CommonId commonId);

    @RequestMapping("getUserByName")
    User getUserByName(@RequestBody User user);

    @RequestMapping("deleteUser")
    boolean deleteUser(@RequestBody CommonId commonId);

    @RequestMapping("userList")
    IPage<User> userList(@RequestBody PageIn<User> pageIn);
}
