package com.cloud.shopping.search.repository;

import com.cloud.shopping.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author Hwj
 * @Date 2019/4/11 19:59
 * @Version 1.0.0
 **/
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {
}
