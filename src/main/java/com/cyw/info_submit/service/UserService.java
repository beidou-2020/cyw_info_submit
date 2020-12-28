package com.cyw.info_submit.service;

import com.cyw.info_submit.entity.User;
import com.cyw.info_submit.entity.query.LoginQuery;

public interface UserService {

    /**
     * 根据主键获取用户信息
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id);

    /**
     * 登录验证用户信息
     * @param query
     * @return
     */
    User loginCheck(LoginQuery query);
}
