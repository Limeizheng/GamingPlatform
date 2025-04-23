package com.cloud.shopping.item.mapper;

import com.cloud.shopping.common.mapper.BaseMapper;
import com.cloud.shopping.item.pojo.Sku;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface SkuMapper extends BaseMapper<Sku> {


    /**
     * 根据id查询sku信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM tb_sku  WHERE id = #{id}")
    Sku queryById(@Param("id") Long id);
}
