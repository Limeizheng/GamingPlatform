package com.cloud.shopping.auth.client;

import com.cloud.shopping.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Version 1.0.0
 **/
@FeignClient("user-service")
public interface UserClient extends UserApi {
}
