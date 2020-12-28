package com.cyw.info_submit.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cyw.info_submit.dao.BackpackMapper;
import com.cyw.info_submit.dao.ProductMapper;
import com.cyw.info_submit.entity.Backpack;
import com.cyw.info_submit.entity.BackpackKey;
import com.cyw.info_submit.entity.Product;
import com.cyw.info_submit.entity.User;
import com.cyw.info_submit.entity.common.PageParam;
import com.cyw.info_submit.entity.dto.AddBackpackDTO;
import com.cyw.info_submit.entity.vo.BackpackListVO;
import com.cyw.info_submit.service.BackpackService;
import com.cyw.info_submit.service.CommonService;
import com.cyw.info_submit.utils.BeanUtil;
import com.cyw.info_submit.utils.BigDecimalUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BackpackServiceImpl implements BackpackService {

    @Resource
    private BackpackMapper backpackMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private CommonService commonService;

    @Override
    public PageInfo<BackpackListVO> listByBp(PageParam pageParam) {
        // 获取登录用户的信息
        User userInfo = commonService.getCurrLoginUserInfo();
        if (Objects.isNull(userInfo)){
            log.error("获取当前登录用户信息为空：{}", JSONObject.toJSONString(userInfo));
            return null;
        }
        Long uid = userInfo.getId();
        // 获取背包数据(用户和物品关系)
        List<Backpack> backpacks = backpackMapper.listByUid(uid);
        if (CollectionUtils.isEmpty(backpacks)){
            return null;
        }

        // 获取背包中的物品信息
        List<Long> pidList = backpacks.stream().map(Backpack::getTpid).collect(Collectors.toList());
        List<Product> productList = productMapper.listByPids(pidList);
        if (CollectionUtils.isEmpty(productList)){
            return null;
        }

        // 封装背包页面VO
        List<BackpackListVO> voList = new ArrayList<>();
        Map<Long, Product> productMap = productList.stream().
                collect(Collectors.toMap(Product::getId, product -> product));
        backpacks.stream().forEach(backpack -> {
            BackpackListVO vo = new BackpackListVO();
            BeanUtil.copyProperties(backpack, vo);
            Product productInfo = productMap.get(backpack.getTpid());
            if (Objects.nonNull(productInfo)){
                BeanUtil.copyProperties(productInfo, vo);
            }

            voList.add(vo);
        });

        PageHelper.startPage(pageParam.getCurrentPageNumber(), pageParam.getPageSize());
        PageInfo<BackpackListVO> pageInfo = new PageInfo<>(voList);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Integer addBackpack(AddBackpackDTO addBackpackDTO) {
        Backpack backpack = new Backpack();
        Long pid = addBackpackDTO.getTpid();
        BigDecimal number = BigDecimal.valueOf(addBackpackDTO.getTpcount());
        BigDecimal price;
        try{
            // 获取登录用户的信息
            User userInfo = commonService.getCurrLoginUserInfo();
            if (Objects.isNull(userInfo)){
                log.error("获取当前登录用户信息为空：{}", JSONObject.toJSONString(userInfo));
                return 0;
            }
            Long userId = userInfo.getId();
            backpack.setTuid(userId);
            // 获取物品信息
            Product product = productMapper.selectByPrimaryKey(pid);
            if (Objects.isNull(product)){
                log.error("未检索到ID={}的物品信息。", JSONObject.toJSONString(pid));
                return 0;
            }
            // 计算总价
            price = product.getPrice();
            BigDecimal totalPrice = number.multiply(price);

            // 校验当前添加的物品是否已在背包中(只检测状态为未提交的)
            BackpackKey key = new BackpackKey();
            key.setTuid(userId);
            key.setTpid(pid);
            Backpack productByBp = backpackMapper.selectByPrimaryKey(key);
            if (Objects.isNull(productByBp)){
                // 首次加入背包
                BeanUtil.copyProperties(addBackpackDTO, backpack);
                backpack.setPtotal(totalPrice);
                int i = backpackMapper.insertSelective(backpack);
                if (1 == i){
                    log.info("物品：{}已成功加入到我的背包中。", JSONObject.toJSONString(backpack));
                    return i;
                }
            }else {
                // 合并背包中的数据
                BigDecimal bpTotalPrice = productByBp.getPtotal();
                BigDecimal addTotalPrice = BigDecimalUtils.add(bpTotalPrice, totalPrice);
                if (Objects.nonNull(addTotalPrice)){
                    productByBp.setPtotal(addTotalPrice);
                }
                BigDecimal bpNumber = BigDecimal.valueOf(productByBp.getTpcount());
                BigDecimal addNumber = BigDecimalUtils.add(bpNumber, number);
                if (Objects.nonNull(addNumber)){
                    productByBp.setTpcount(addNumber.longValue());
                }
                int i = backpackMapper.updateByPrimaryKeySelective(productByBp);
                if (1 == i){
                    log.info("物品：{}已成功合并到我的背包中。", JSONObject.toJSONString(productByBp));
                    return i;
                }
            }
        }catch (Exception ex){
            log.error("加入背包异常", ex);
        }
        return 0;
    }
}
