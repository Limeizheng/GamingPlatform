package com.cloud.shopping.item.service;

import com.cloud.shopping.common.enums.ExceptionEnum;
import com.cloud.shopping.common.exception.CloudException;
import com.cloud.shopping.item.mapper.SkuMapper;
import com.cloud.shopping.item.pojo.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkuService {
    @Autowired
    private SkuMapper skuMapper;

    public Sku querySkuById(Long id){
        Sku sku = skuMapper.queryById(id);
        if(sku == null){
            throw new CloudException(ExceptionEnum.SKU_NOT_FOUND);
        }
        return sku;
    }
}
