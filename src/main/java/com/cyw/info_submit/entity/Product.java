package com.cyw.info_submit.entity;

import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class Product {
    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String pname;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 规格单位
     */
    private String quality;

    /**
     * 厂家
     */
    private String factory;

    /**
     * 编码
     */
    private String pnumber;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;
}