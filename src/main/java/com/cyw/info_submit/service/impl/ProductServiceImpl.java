package com.cyw.info_submit.service.impl;

import com.cyw.info_submit.dao.ProductMapper;
import com.cyw.info_submit.entity.Product;
import com.cyw.info_submit.entity.common.PageParam;
import com.cyw.info_submit.entity.query.ProductQuery;
import com.cyw.info_submit.service.ProductService;
import com.cyw.info_submit.utils.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public Product selectByPrimaryKey(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Product> listByPage(PageParam pageQuery, ProductQuery query) {
        PageHelper.startPage(pageQuery.getCurrentPageNumber(), pageQuery.getPageSize());
        Product productQuery = new Product();
        BeanUtil.copyProperties(query, productQuery);
        List<Product> list = productMapper.findByQuery(productQuery);
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
