package com.cyw.info_submit.controller;

import com.cyw.info_submit.controller.common.Result;
import com.cyw.info_submit.entity.User;
import com.cyw.info_submit.service.UserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 根据主键查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/userDetails/{id}")
    @ResponseBody
    public Result userDetails(@PathVariable("id") Long id){
        User user = userService.selectByPrimaryKey(id);
        return Result.ok(user);
    }
}
