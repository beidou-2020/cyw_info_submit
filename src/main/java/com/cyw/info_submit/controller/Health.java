package com.cyw.info_submit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Health {

    /**
     * 服务心跳检查
     * @return
     */
    @GetMapping("Heartbeat")
    public String Heartbeat(){
        return "Service is normal";
    }


}
