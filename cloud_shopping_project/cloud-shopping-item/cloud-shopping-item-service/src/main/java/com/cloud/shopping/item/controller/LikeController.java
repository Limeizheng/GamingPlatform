package com.cloud.shopping.item.controller;

import com.alibaba.fastjson.JSONArray;
import com.cloud.shopping.item.pojo.Like;
import com.cloud.shopping.item.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("like")
@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;
    @GetMapping("all")
    public ResponseEntity<String> getAllSku(){
        List likes = likeService.queryLike();
        JSONArray jsonArray = new JSONArray(likes);
        System.out.println(jsonArray.toJSONString());
        return ResponseEntity.ok(jsonArray.toJSONString());
    }
}
