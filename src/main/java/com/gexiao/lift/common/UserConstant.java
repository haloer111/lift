package com.gexiao.lift.common;

/**
 * 用户常量
 *
 * @author : lzx
 * created on 2018/12/19
 */
public interface UserConstant {

    int PASSWORD_ENCODER_SALT = 7;

    /**
     * jwt存储用户ID的key
     */
    String USER_ID = "userId";

    /**
     * jwt存储用户名的key
     */
    String NAME = "name";

}
