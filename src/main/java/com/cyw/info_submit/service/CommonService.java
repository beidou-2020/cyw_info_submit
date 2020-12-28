package com.cyw.info_submit.service;

import com.cyw.info_submit.entity.User;

public interface CommonService {

    /**
     * 获取当前登录的用户信息
     * @return
     */
    User getCurrLoginUserInfo();

    /**
     * 退出当前登录用户的信息
     * @return
     */
    Boolean exitCurrLoginUser();
}
