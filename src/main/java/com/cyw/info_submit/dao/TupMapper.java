package com.cyw.info_submit.dao;

import com.cyw.info_submit.entity.Tup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TupMapper {
    int deleteByPrimaryKey(Long id);

    /**
     * 保存一条提交记录
     * @param record
     * @return
     */
    int insertSelective(Tup record);

    /**
     * 根据主键获取物品提交记录
     * @param id
     * @return
     */
    Tup selectByPrimaryKey(@Param("id") Long id);

    /**
     * 获取我的订单列表
     * @param uid
     * @return
     */
    List<Tup> myOrders(@Param("uid") Long uid);

    int updateByPrimaryKeySelective(Tup record);

    int updateByPrimaryKey(Tup record);
}