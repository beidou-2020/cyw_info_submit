package com.cyw.info_submit.controller;

import com.cyw.info_submit.controller.common.Result;
import com.cyw.info_submit.entity.common.PageParam;
import com.cyw.info_submit.entity.dto.AddBackpackDTO;
import com.cyw.info_submit.entity.vo.BackpackListVO;
import com.cyw.info_submit.enumerate.ResultCode;
import com.cyw.info_submit.service.BackpackService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/backpack")
@Slf4j
public class BackpackController {

    @Resource
    private BackpackService backpackService;

    /**
     * 获取我的背包列表数据VO
     * @return
     */
    @GetMapping("/pbProductList")
    public ModelAndView pbProductList(@Valid PageParam pageParam){
        PageInfo<BackpackListVO> pageInfo = backpackService.listByBp(pageParam);
        return new ModelAndView("bp_list").
                addObject("list", pageInfo.getList()).
                addObject("totalPage", pageInfo.getPages()).
                addObject("currentPageNumber", pageParam.getCurrentPageNumber());
    }

    /**
     * 加入一个物品到我的背包中
     * @param addBackpackDTO
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result addBackpack(@Valid AddBackpackDTO addBackpackDTO, HttpServletRequest request){
        Integer addResult = backpackService.addBackpack(addBackpackDTO);
        if (1 == addResult.intValue()){
            return Result.ok(addResult);
        }
        return Result.fail(ResultCode.SERVER_ERROR.code(), ResultCode.SERVER_ERROR.msg());
    }
}
