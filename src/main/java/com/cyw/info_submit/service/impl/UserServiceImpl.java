package com.cyw.info_submit.service.impl;

import com.cyw.info_submit.dao.UserMapper;
import com.cyw.info_submit.entity.User;
import com.cyw.info_submit.entity.query.LoginQuery;
import com.cyw.info_submit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public User loginCheck(LoginQuery query) {
        User user = userMapper.loginCheck(query);
        return user;
    }
}
