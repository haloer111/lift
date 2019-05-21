package com.gexiao.lift.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gexiao.lift.entity.UserRole;

import java.util.List;

/**
 * 用户角色service
 * @Auther: gexiao
 * @Date: 2019/4/9 14:57
 * @Description:
 */
public interface IUserRoleService extends IService<UserRole> {


    /**
     * 根据用户id获取角色集合
     * @param userId 用户id
     * @return
     */
    List<String> rolesByUserId(String userId);
}
