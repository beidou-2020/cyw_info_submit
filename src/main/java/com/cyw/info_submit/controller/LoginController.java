package com.cyw.info_submit.controller;

import com.cyw.info_submit.constant.SessionConstant;
import com.cyw.info_submit.controller.common.Result;
import com.cyw.info_submit.entity.User;
import com.cyw.info_submit.entity.query.LoginQuery;
import com.cyw.info_submit.enumerate.ResultCode;
import com.cyw.info_submit.service.CommonService;
import com.cyw.info_submit.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/")
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private HttpSession httpSession;

    @Resource
    private CommonService commonService;

    /**
     * 预购系统登录页面
     * @return
     */
    @GetMapping("/")
    public ModelAndView toLogin(){
        return new ModelAndView("login");
    }

    /**
     * 登录
     * @param query
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(@Valid LoginQuery query){
        User userInfo = userService.loginCheck(query);
        if (Objects.isNull(userInfo)){
            return new ModelAndView("login").
                    addObject("queryInfo", query);
        }
        // 将登录成功后的用户信息存入Session
        httpSession.setAttribute(SessionConstant.CURR_lOGIN_USERINFO, userInfo);
        return new ModelAndView("redirect:/product/pageData");
    }

    /**
     * 退出当前登录用户
     * @return
     */
    @PostMapping("/exit")
    public Result exitCurrLoginUser(){
        Boolean result = commonService.exitCurrLoginUser();
        if (!result){
            return Result.fail(ResultCode.SERVER_ERROR.code(), ResultCode.SERVER_ERROR.msg());
        }
        return Result.ok(result);
    }

}
