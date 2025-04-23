package com.cloud.shopping.user.service.impl;

import com.cloud.shopping.common.enums.ExceptionEnum;
import com.cloud.shopping.common.exception.CloudException;
import com.cloud.shopping.common.utils.NumberUtils;
import com.cloud.shopping.user.pojo.User;
import com.cloud.shopping.user.service.UserService;
import com.cloud.shopping.user.mapper.UserMapper;
import com.cloud.shopping.user.utils.CodecUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "user:verify:phone";

    private static final String KEY_PREFIX2 = "cloudshopping:user:info";

    /**
     * Check whether the username and mobile phone number are available
     * @param data
     * @param type
     * @return
     */
    @Override
    public Boolean checkData(String data, Integer type) {
        //判断数据类型
        User user = new User();
        switch (type){
            case 1 :
                user.setUsername(data);
                break;
            case 2 :
                user.setPhone(data);
                break;
            default:
                throw new CloudException(ExceptionEnum.INVALID_USER_DATA_TYPE);
        }
        return userMapper.selectCount(user) == 0;
    }

    @Override
    public void sendCode(String phone) {
        String key=KEY_PREFIX+phone;// Redis key  user:verify:phone18812345678

        String code= NumberUtils.generateCode(6);// random 6‑digit string
        Map<String,String> msg=new HashMap<>();
        msg.put("phone",phone);
        msg.put("code",code);

        amqpTemplate.convertAndSend(
                "ly.sms.exchange",// exchange
                "sms.verify.code",// routing‑key
                msg);  // message body. An SMS micro‑service consumes that queue and calls Alibaba SMS

        redisTemplate.opsForValue().set(key,code,5,TimeUnit.MINUTES);//Cache code in Redis (TTL 5min). After 5 min the code expires automatically.
    }

    @Override
    public void register(User user, String code) {

        String cacheCode = redisTemplate.opsForValue().get(KEY_PREFIX + user.getPhone());


        if(!StringUtils.equals(code,cacheCode)){
            throw  new CloudException(ExceptionEnum.INVALID_VERIFY_CODE);
        }


        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);

        user.setPassword(CodecUtils.md5Hex(user.getPassword(),salt));

        user.setCreated(new Date());
        userMapper.insert(user);

    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {

        User record = new User();
        record.setUsername(username);
        User user = userMapper.selectOne(record);

        if(user == null){
            throw new CloudException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        }

        if(!StringUtils.equals(user.getPassword(),CodecUtils.md5Hex(password,user.getSalt()))){
            throw new CloudException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        }

        return user;
    }

    /**
     * Determine whether it is an administrator
     * @param username
     * @param password
     * @return
     */
    @Override
    public Boolean isAdmin(String username, String password) {
        //查询用户
        User record = new User();
        record.setUsername(username);
        User user = userMapper.selectOne(record);

        Long id = user.getId();

        if(id == 31){
            return true;
        }
        return false;
    }


}
