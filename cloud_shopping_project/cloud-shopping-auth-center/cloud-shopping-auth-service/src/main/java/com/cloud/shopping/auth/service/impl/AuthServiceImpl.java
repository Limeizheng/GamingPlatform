package com.cloud.shopping.auth.service.impl;

import com.cloud.shopping.auth.service.AuthService;
import com.cloud.shopping.auth.client.UserClient;
import com.cloud.shopping.auth.config.JwtProperties;
import com.cloud.shopping.auth.entiy.UserInfo;
import com.cloud.shopping.auth.utils.JwtUtils;
import com.cloud.shopping.common.enums.ExceptionEnum;
import com.cloud.shopping.common.exception.CloudException;
import com.cloud.shopping.user.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Slf4j
@Service
@EnableConfigurationProperties(JwtProperties.class)
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserClient userClient;

    @Autowired
    private  JwtProperties prop;

    // 临时解决方案 定义HashMap用于存放Token
    private HashMap<String,String> tokenMap = new HashMap<>();
    @Override
    public String login(String username, String password) {
        try{
            //校验用户名和密码
            User user = userClient.queryUserByUsernameAndPassword(username, password);
            user.setUsername(username);
            user.setPassword(password);
            //判断
            if(user == null){
                System.out.println("未查到用户信息:"+username);
                throw  new CloudException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
            }
            //生成token
            String token = JwtUtils.generateToken(new UserInfo(user.getId(), username),
                    prop.getPrivateKey(), prop.getExpire());
            return token;

        }catch(Exception e){
            log.error("[授权中心] 用户名或密码有误，用户名称：{}",username,e);
            throw new CloudException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        }
    }

    @Override
    public Boolean adminLogin(String username, String password) {
            //判断是否为管理员
            Boolean queryAdmin = userClient.queryAdmin(username, password);
            if(queryAdmin){
                return true;
            }
            log.error("[授权中心] 该用户不是管理员，用户名称：{}",username);
            return false;
    }
}
