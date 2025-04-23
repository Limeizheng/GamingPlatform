package com.cloud.shopping.item.controller;

import com.cloud.shopping.item.pojo.Sku;
import com.cloud.shopping.item.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("sku")
@RestController
public class SkuController {
    @Autowired
    private SkuService skuService;
    @GetMapping("detail/{skuid}")
    public ResponseEntity<String> getSkuById(@PathVariable("skuid") String skuid){
        System.out.println(skuid);
        Sku sku = skuService.querySkuById(new Long(skuid));
        if (sku!=null)
            System.out.println(sku.getTitle());
        return ResponseEntity.ok("");
    }
}
