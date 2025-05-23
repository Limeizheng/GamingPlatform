package com.cloud.shopping.item.api;

import com.cloud.shopping.item.pojo.SpecGroup;
import com.cloud.shopping.item.pojo.SpecParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SpecificationApi {
    @GetMapping("spec/params")
    List<SpecParam> querySpecParamList(
            @RequestParam(value="gid", required = false) Long gid,
            @RequestParam(value="cid", required = false) Long cid,
            @RequestParam(value="searching", required = false) Boolean searching
    );

    @GetMapping("spec/group")
    List<SpecGroup> queryGroupByCid(@RequestParam("cid") Long cid);


    // 查询规格参数组，及组内参数
//    @GetMapping("/spec/{cid}")
//    List<SpecGroup> querySpecsByCid(@PathVariable("cid") Long cid);

}
