package com.cloud.shopping.user.api;

import com.cloud.shopping.user.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserApi {
    @GetMapping("/query")
    User queryUserByUsernameAndPassword(
            @RequestParam("username") String username,
            @RequestParam("password") String password);

    @GetMapping("/queryAdmin")
    public Boolean queryAdmin(
            @RequestParam("username") String username,
            @RequestParam("password") String password);
}
