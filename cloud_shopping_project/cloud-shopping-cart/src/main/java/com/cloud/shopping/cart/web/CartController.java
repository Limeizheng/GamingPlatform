package com.cloud.shopping.cart.web;

import com.cloud.shopping.cart.pojo.Cart;
import com.cloud.shopping.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 新增购物车
     * @param
     * @return
     */
    @PostMapping("add")
    public ResponseEntity<Void> addCart(@RequestParam("id") String skuId,@RequestParam("title") String title,@RequestParam("images") String images,@RequestParam("ownSpec") String ownSpec,@RequestParam("price") String price) {
        System.out.println("登录验证通过，将商品加入购物车Cart:");
        Cart cart = new Cart();
        cart.setSkuId(new Long(skuId));
        cart.setTitle(title);
        cart.setNum(1);
        cart.setImage(images);
        cart.setOwnSpec(ownSpec);
        cart.setPrice(new Long(price));
        cartService.addCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 查询购物车列表
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Cart>> queryCartList(){
        System.out.println("购物车列表");
        List<Cart> cartList = cartService.quertyCartList();
        for (Cart cart:cartList) {
            System.out.println(cart.getTitle());
        }
        return ResponseEntity.ok(cartList);
    }

    /**
     * 修改购物车商品数量
     * @param skuId
     * @param num
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateCartNum(@RequestParam("id") Long skuId,
                                              @RequestParam("num") Integer num){
        log.info("获取到的skuId:{}",skuId);
        log.info("获取到的num:{}",num);

        cartService.updateNum(skuId,num);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 删除购物车数据
     * @param skuId
     * @return
     */
    @DeleteMapping("{skuId}")
    public ResponseEntity<Void> deleteCart(@PathVariable("skuId") Long skuId){
        cartService.deleteCart(skuId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}