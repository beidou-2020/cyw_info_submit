package com.cyw.info_submit.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cyw.info_submit.dao.BackpackMapper;
import com.cyw.info_submit.dao.ProductMapper;
import com.cyw.info_submit.dao.TupMapper;
import com.cyw.info_submit.entity.BackpackKey;
import com.cyw.info_submit.entity.Product;
import com.cyw.info_submit.entity.Tup;
import com.cyw.info_submit.entity.User;
import com.cyw.info_submit.entity.common.PageParam;
import com.cyw.info_submit.entity.dto.SubmitDTO;
import com.cyw.info_submit.exception.ServerErrorException;
import com.cyw.info_submit.service.CommonService;
import com.cyw.info_submit.service.TupService;
import com.cyw.info_submit.utils.BigDecimalUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class TupServiceImpl implements TupService {

    @Resource
    private TupMapper tupMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private BackpackMapper backpackMapper;

    @Resource
    private CommonService commonService;

    @Override
    public Tup selectByPrimaryKey(Long id) {
        return tupMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Integer submit(SubmitDTO submitDTO) {
        Tup tup = new Tup();
        Long pid = submitDTO.getTpid();
        Long number = submitDTO.getTpcount();
        try{
            // 获取登录用户的信息
            User userInfo = commonService.getCurrLoginUserInfo();
            if (Objects.isNull(userInfo)){
                log.error("获取当前登录用户信息为空：{}", JSONObject.toJSONString(userInfo));
                return 0;
            }
            tup.setTuid(userInfo.getId());
            tup.setUsername(userInfo.getName());
            tup.setTpid(pid);
            tup.setTpcount(number);

            // 计算总价
            Product product = productMapper.selectByPrimaryKey(pid);
            if (Objects.isNull(product)){
                log.error("未检索到ID={}的物品信息。", JSONObject.toJSONString(pid));
                return 0;
            }
            tup.setPname(product.getPname());
            BigDecimal numberBigDecimal = new BigDecimal(String.valueOf(number));
            BigDecimal totalPrice = BigDecimalUtils.multiply(numberBigDecimal, product.getPrice());
            tup.setPtotal(totalPrice);

            // 生成提交编号
            String subNum = String.valueOf(System.currentTimeMillis());
            tup.setSubmitNumber(subNum);

            // 删除背包数据, 保存提交数据
            BackpackKey bpKey = new BackpackKey();
            bpKey.setTpid(pid);
            bpKey.setTuid(userInfo.getId());
            int delete = backpackMapper.deleteByPrimaryKey(bpKey);
            int insert = tupMapper.insertSelective(tup);
            if (1 != insert || 1 != delete){
                throw new ServerErrorException("提交数据时发生服务器异常");
            }

            log.info("背包信息：{}提交成功。", JSONObject.toJSONString(tup));
            return 1;
        }catch (Exception ex){
            log.error("提交一条背包数据发生异常", ex);
        }
        return 0;
    }

    @Override
    public PageInfo<Tup> myOrders(PageParam pageParam) {
        // 获取登录用户的信息
        User userInfo = commonService.getCurrLoginUserInfo();
        if (Objects.isNull(userInfo)){
            log.error("获取当前登录用户信息为空：{}", JSONObject.toJSONString(userInfo));
            return null;
        }

        PageHelper.startPage(pageParam.getCurrentPageNumber(), pageParam.getPageSize());
        List<Tup> list = tupMapper.myOrders(userInfo.getId());
        PageInfo<Tup> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
