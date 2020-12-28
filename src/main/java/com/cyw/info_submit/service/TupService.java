package com.cyw.info_submit.service;

import com.cyw.info_submit.entity.Tup;
import com.cyw.info_submit.entity.common.PageParam;
import com.cyw.info_submit.entity.dto.SubmitDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TupService {

    /**
     * 根据主键获取物品提交记录
     * @param id
     * @return
     */
    Tup selectByPrimaryKey(Long id);

    /**
     * 提交
     * @param submitDTO
     * @return
     */
    Integer submit(SubmitDTO submitDTO);

    /**
     * 获取我的订单列表
     * @param pageParam
     * @return
     */
    PageInfo<Tup> myOrders(PageParam pageParam);
}
