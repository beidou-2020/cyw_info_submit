package com.cyw.info_submit.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class User {
    /**
     * 主键
     */
    private Long id;

    /**
     * 账号
     */
    private String name;

    /**
     * 单位名称
     */
    private String company;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;
}