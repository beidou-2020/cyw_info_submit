package com.cyw.info_submit.entity.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
public class SubmitDTO implements Serializable {

    /**
     * 物品id
     */
    @NotNull(message = "提交背包数据时请选择物品")
    private Long tpid;

    /**
     * 产品数量
     */
    @NotNull(message = "提交背包数据时请选择预购物品的数量")
    private Long tpcount = 1L;
}
