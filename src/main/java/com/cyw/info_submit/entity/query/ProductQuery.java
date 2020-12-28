package com.cyw.info_submit.entity.query;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductQuery {

    /**
     * 名称
     */
    private String pname;

    /**
     * 厂家
     */
    private String factory;

    /**
     * 编码
     */
    private String pnumber;
}
