package com.cyw.info_submit.entity.vo;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class BackpackListVO implements Serializable {
    /**
     * 产品数量
     */
    private Long tpcount;

    /**
     * 产品总价
     */
    private BigDecimal ptotal;

    /**
     * 物品id
     */
    private Long tpid;

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
}
