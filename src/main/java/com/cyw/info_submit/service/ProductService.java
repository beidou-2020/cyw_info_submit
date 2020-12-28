package com.cyw.info_submit.service;

import com.cyw.info_submit.entity.Product;
import com.cyw.info_submit.entity.common.PageParam;
import com.cyw.info_submit.entity.query.ProductQuery;
import com.github.pagehelper.PageInfo;

public interface ProductService {

    /**
     * 根据主键查询物品信息
     * @param id
     * @return
     */
    Product selectByPrimaryKey(Long id);

    /**
     * 复合条件分页查询物品列表
     * @param query
     * @return
     */
    PageInfo<Product> listByPage(PageParam pageParam, ProductQuery query);



}
