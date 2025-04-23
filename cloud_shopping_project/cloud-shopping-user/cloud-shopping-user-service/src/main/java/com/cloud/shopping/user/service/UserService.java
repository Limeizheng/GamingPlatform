package com.cloud.shopping.user.service;

import com.cloud.shopping.user.pojo.User;
public interface UserService {

    /**
     * Check whether the username and mobile phone number are available
     * @param data
     * @param type
     * @return
     */
    Boolean checkData(String data, Integer type);

    /**
     * Send text messages
     * @param phone
     */
    void sendCode(String phone);

    /**
     * User registration
     * @param user
     * @param code
     */
    void register(User user, String code);

    /**
     * Query the user based on the username and password
     * @param username
     * @param password
     * @return
     */
    User queryUserByUsernameAndPassword(String username, String password);

    /**
     * Determine whether it is an administrator. If it is, return true
     * @param username
     * @param password
     * @return
     */
    Boolean isAdmin(String username, String password);
}
