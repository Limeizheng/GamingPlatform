package com.cloud.shopping.search.web;


import com.cloud.shopping.common.vo.PageResult;
import com.cloud.shopping.search.pojo.Goods;
import com.cloud.shopping.search.service.SearchService;
import com.cloud.shopping.search.pojo.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class SearchController{

    @Autowired
    private SearchService searchService;


    /**
     * 搜索功能
     * @param searchRequest
     * @return
     */
    @PostMapping("page")
    public ResponseEntity<PageResult<Goods>> search(@RequestBody SearchRequest searchRequest){
       return ResponseEntity.ok(searchService.search(searchRequest));
    }




}
