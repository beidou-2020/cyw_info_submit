package com.cyw.info_submit.dao;

import com.cyw.info_submit.entity.Backpack;
import com.cyw.info_submit.entity.BackpackKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackpackMapper {
    /**
     * 清除背包中的数据
     * @param key
     * @return
     */
    int deleteByPrimaryKey(BackpackKey key);

    int insert(Backpack record);

    /**
     * 加入一个物品到我的背包中
     * @param record
     * @return
     */
    int insertSelective(Backpack record);

    /**
     * 根据联合主键查询记录
     * @param key
     * @return
     */
    Backpack selectByPrimaryKey(BackpackKey key);

    /**
     * 根据主键更新背包中的记录(合并数据)
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Backpack record);

    /**
     * 获取我的背包中的物品列表
     * @param uid
     * @return
     */
    List<Backpack> listByUid(@Param("uid") Long uid);

}