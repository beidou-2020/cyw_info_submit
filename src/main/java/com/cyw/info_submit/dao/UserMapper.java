package com.cyw.info_submit.dao;


import com.cyw.info_submit.entity.User;
import com.cyw.info_submit.entity.query.LoginQuery;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * 登录验证用户信息
     * @param query
     * @return
     */
    User loginCheck(LoginQuery query);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    /**
     * 根据主键获取用户信息
     * @param id
     * @return
     */
    User selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}