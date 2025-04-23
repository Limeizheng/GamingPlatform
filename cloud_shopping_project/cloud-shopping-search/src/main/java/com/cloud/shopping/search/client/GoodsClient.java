package com.cloud.shopping.search.client;

import com.cloud.shopping.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {


}
