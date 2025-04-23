package com.cloud.shopping.item.mapper;

import com.cloud.shopping.common.mapper.BaseMapper;
import com.cloud.shopping.item.pojo.Like;
import com.cloud.shopping.item.pojo.Sku;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface LikeMapper extends BaseMapper<Sku> {

    @Select("SELECT * FROM tb_like")
    List<Like> queryLike();
}
