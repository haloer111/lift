package com.gexiao.lift.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gexiao.lift.entity.RoleAuth;

import java.util.List;

/**
 * @Auther: gexiao
 * @Date: 2019/4/9 14:57
 * @Description:
 */
public interface IRoleAuthService extends IService<RoleAuth> {


    /**
     * 根据角色id获取角色资源列表
     * @param roleId
     * @return
     */
    List<RoleAuth> listByRoleId(String roleId);
}
