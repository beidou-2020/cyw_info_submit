package com.cyw.info_submit.controller;

import com.cyw.info_submit.controller.common.Result;
import com.cyw.info_submit.entity.Tup;
import com.cyw.info_submit.entity.common.PageParam;
import com.cyw.info_submit.entity.dto.SubmitDTO;
import com.cyw.info_submit.enumerate.ResultCode;
import com.cyw.info_submit.service.TupService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/tup")
public class TupController {

    @Resource
    private TupService tupService;

    /**
     * 根据主键查看提交记录
     * @param id
     * @return
     */
    @GetMapping("/tupDetails/{id}")
    @ResponseBody
    public Result tupDetails(@PathVariable("id") Long id){
        Tup tup = tupService.selectByPrimaryKey(id);
        return Result.ok(tup);
    }

    /**
     * 提交一条背包数据
     * @param submitDTO
     * @return
     */
    @PostMapping("/submit")
    @ResponseBody
    public Result submit(@Valid SubmitDTO submitDTO){
        Integer submitRes = tupService.submit(submitDTO);
        if (1 == submitRes.intValue()){
            return Result.ok(submitRes);
        }
        return Result.fail(ResultCode.SERVER_ERROR.code(), ResultCode.SERVER_ERROR.msg());
    }

    /**
     * 获取用户的提交列表
     * @return
     */
    @GetMapping("/orderList")
    public ModelAndView orderList(@Valid PageParam pageParam){
        PageInfo<Tup> pageInfo = tupService.myOrders(pageParam);
        return new ModelAndView("orders_list").
                addObject("list", pageInfo.getList()).
                addObject("totalPage", pageInfo.getPages()).
                addObject("currentPageNumber", pageParam.getCurrentPageNumber());
    }

}
