package com.cyw.info_submit.controller;

import com.cyw.info_submit.controller.common.Result;
import com.cyw.info_submit.entity.Product;
import com.cyw.info_submit.entity.common.PageParam;
import com.cyw.info_submit.entity.query.ProductQuery;
import com.cyw.info_submit.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    /**
     * 根据主键查看物品详情
     * @param id
     * @return
     */
    @GetMapping("/productDetails/{id}")
    @ResponseBody
    public Result productDetails(@PathVariable("id") Long id){
        Product product = productService.selectByPrimaryKey(id);
        return Result.ok(product);
    }

    /**
     * 分页查询物品列表数据
     * @param pageParam
     * @param query
     * @return
     */
    @GetMapping("/pageData")
    public ModelAndView pageData(@Valid PageParam pageParam, ProductQuery query){
        PageInfo<Product> pageInfo = productService.listByPage(pageParam, query);
        return new ModelAndView("index").
                addObject("list", pageInfo.getList()).
                addObject("totalPage", pageInfo.getPages()).
                addObject("currPageNumber", pageParam.getCurrentPageNumber());
    }

}
