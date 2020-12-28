package com.cyw.info_submit.entity;

import lombok.Data;
import lombok.ToString;

/**
 * 联合主键
 */
@Data
@ToString
public class BackpackKey {
    /**
     * 用户id
     */
    private Long tuid;

    /**
     * 物品id
     */
    private Long tpid;
}