package com.cloud.shopping.order.service.api;

import com.cloud.shopping.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cloudshopping-gateway", path = "/api/item")
public interface GoodsService extends GoodsApi {
}
