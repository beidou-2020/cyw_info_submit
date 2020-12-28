package com.cyw.info_submit.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 集成联合主键对象
 */
@Data
@ToString
public class Backpack extends BackpackKey {
    /**
     * 用户id
     */
    private Long tuid;

    /**
     * 物品id
     */
    private Long tpid;

    /**
     * 产品数量
     */
    private Long tpcount;

    /**
     * 产品总价
     */
    private BigDecimal ptotal;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;
}