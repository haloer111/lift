package com.gexiao.lift.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gexiao.lift.entity.User;

import java.util.Optional;

/**
 * @Auther: gexiao
 * @Date: 2019/4/9 14:57
 * @Description:
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名获取用户信息
     * @param name
     * @return
     */
    Optional<User> getUserByName(String name);

    /**
     * 根据登录名获取用户信息
     * @param name
     * @return
     */
    Optional<User> getUserByLoginName(String name);

    /**
     * 登录,成功返回token
     * @param loginName 用户名
     * @param loginPassword 密码
     * @return token
     */
    String login(String loginName, String loginPassword);

    /**
     * 修改密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    boolean updatePassword(String oldPassword, String newPassword);
}
