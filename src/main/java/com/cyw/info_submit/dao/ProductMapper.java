package com.cyw.info_submit.dao;


import com.cyw.info_submit.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {

    /**
     * 根据id批量获取物品信息
     * @param idList
     * @return
     */
    List<Product> listByPids(@Param("idList") List<Long> idList);

    /**
     * 复合条件查询物品数据列表
     * @param product
     * @return
     */
    List<Product> findByQuery(Product product);

    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    /**
     * 根据主键查询物品信息
     * @param id
     * @return
     */
    Product selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}