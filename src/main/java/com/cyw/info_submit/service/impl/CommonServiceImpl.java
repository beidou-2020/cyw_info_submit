package com.cyw.info_submit.service.impl;

import com.cyw.info_submit.constant.SessionConstant;
import com.cyw.info_submit.entity.User;
import com.cyw.info_submit.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;


@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    @Resource
    private HttpServletRequest httpServletRequest;

    @Resource
    private HttpSession httpSession;

    @Override
    public User getCurrLoginUserInfo() {
        User userInfo = (User) httpServletRequest.getSession().getAttribute(SessionConstant.CURR_lOGIN_USERINFO);
        if (Objects.isNull(userInfo)){
            return new User();
        }
        return userInfo;
    }

    @Override
    public Boolean exitCurrLoginUser() {
        User userInfo = (User) httpSession.getAttribute(SessionConstant.CURR_lOGIN_USERINFO);
        if (Objects.isNull(userInfo)){
            return true;
        }

        httpSession.invalidate();
        if (Objects.isNull(httpSession.getAttribute(SessionConstant.CURR_lOGIN_USERINFO))){
            return true;
        }
        return false;
    }
}
