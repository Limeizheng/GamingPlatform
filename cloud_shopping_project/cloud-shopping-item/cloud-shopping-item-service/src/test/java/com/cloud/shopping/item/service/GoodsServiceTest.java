package com.cloud.shopping.item.service;

import com.cloud.shopping.common.dto.CartDTO;
import com.cloud.shopping.item.pojo.Like;
import com.cloud.shopping.item.pojo.Sku;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTest {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private LikeService likeService;

    @Autowired
    private SkuService skuService;

    @org.junit.Test
    public void decreaseStock() {
        List<CartDTO> list = Arrays.asList(new CartDTO(2600242L, 2), new CartDTO(2600248L, 2));
        goodsService.decreaseStock(list);
    }
    @org.junit.Test
    public void likeService() {
        List<Like> likes = likeService.queryLike();
        for (Like like:likes) {
            System.out.println(like.getImg());
            System.out.println(like.getTitle());
        }
    }
    @org.junit.Test
    public void skuService() {
        Sku sku = skuService.querySkuById(new Long("22867877061"));
        System.out.println(sku.getTitle());
    }
}