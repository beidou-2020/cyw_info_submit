package com.cyw.info_submit.service;

import com.cyw.info_submit.entity.common.PageParam;
import com.cyw.info_submit.entity.dto.AddBackpackDTO;
import com.cyw.info_submit.entity.vo.BackpackListVO;
import com.github.pagehelper.PageInfo;

public interface BackpackService {

    /**
     * 加入一个物品到我的背包中
     * @param addBackpackDTO
     * @return
     */
    Integer addBackpack(AddBackpackDTO addBackpackDTO);

    /**
     * 获取背包中的物品列表
     * @return
     */
    PageInfo<BackpackListVO> listByBp(PageParam pageParam);

}
