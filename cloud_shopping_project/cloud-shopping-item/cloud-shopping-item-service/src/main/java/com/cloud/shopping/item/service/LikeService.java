package com.cloud.shopping.item.service;

import com.cloud.shopping.item.mapper.BrandMapper;
import com.cloud.shopping.item.mapper.LikeMapper;
import com.cloud.shopping.item.pojo.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    private LikeMapper likeMapper;

    public List<Like> queryLike(){
       return likeMapper.queryLike();
    };
}
