package com.cloud.shopping.auth.web;

import com.cloud.shopping.auth.service.AuthService;
import com.cloud.shopping.auth.config.JwtProperties;
import com.cloud.shopping.auth.entiy.UserInfo;
import com.cloud.shopping.auth.utils.JwtUtils;
import com.cloud.shopping.common.enums.ExceptionEnum;
import com.cloud.shopping.common.exception.CloudException;
import com.cloud.shopping.common.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties prop;

    @Value("${jwt.cookieName}")
    private String cookieName;

    UserInfo userInfo = new UserInfo();
    /**
     * 登录授权
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public ResponseEntity<String> login(
            @RequestParam("username") String username,@RequestParam("password") String password,
            HttpServletRequest request, HttpServletResponse response
    ){
        System.out.println("用户登录授权服务Auth："+"用户名："+username+"密码："+password);
        //登录
        String token=authService.login(username,password);
        System.out.println("用户生成登录Token："+token);
        //TODO 写入cookie
        CookieUtils.newBuilder(response).httpOnly().request(request)
        .build(cookieName,token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    /**
     * 校验用户登录状态
     * @param token
     * @return
     */
    @GetMapping("verify")
    public ResponseEntity<UserInfo> verifyUser(
            String token,
            HttpServletRequest request, HttpServletResponse response
    ) {
        System.out.println("用户登录校验verify：");
        Cookie[] cookies = request.getCookies();
        for (Cookie c:cookies) {
            if ("CS_TOKEN".equals(c.getName())){
                System.out.println("Cookie名称："+c.getName());
                System.out.println("Cookie值："+c.getValue());
                token=c.getValue();
            }
        }
        try {
            // 解析token
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
            System.out.println("校验通过，根据Token解析用户名称："+userInfo.getUsername());
            // 刷新token,重新生成token
            String newToken = JwtUtils.generateToken(userInfo,
                    prop.getPrivateKey(), prop.getExpire());
            System.out.println("新生成的Token："+newToken);
            // 然后写入cookie中
            // 将token写入cookie,并指定httpOnly为true，防止通过JS获取和修改
            CookieUtils.newBuilder(response).httpOnly().request(request)
                    .build(cookieName,newToken);
            // 已登录，返回用户信息
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            // token已过期，或者 token被篡改
            throw new CloudException(ExceptionEnum.UN_AUTHORIZED);
        }
    }

/*校验管理员登录*/
    @PostMapping("adminLogin")
    public ResponseEntity<String> adminLogin(
            @RequestParam("username") String username,@RequestParam("password") String password,
            HttpServletRequest request, HttpServletResponse response
    ){
        long id =31;
        userInfo.setId(id);
        userInfo.setUsername(username);
        log.info("管理员登录输入："+username+":"+password);
        //登录
        String token=authService.login(username,password);
        log.info("token："+token);
        //管理员判断
        Boolean adminLogin = true;
                //authService.adminLogin(username, password);
        if(!adminLogin){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //TODO 写入cookie
        try {
            CookieUtils.newBuilder(response).httpOnly().request(request)
                    .build(cookieName,token);
        }catch (Exception e){
            log.info("异常");
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
