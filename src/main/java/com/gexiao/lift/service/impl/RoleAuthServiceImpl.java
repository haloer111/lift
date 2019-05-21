package com.gexiao.lift.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gexiao.lift.dao.RoleAuthMapper;
import com.gexiao.lift.entity.RoleAuth;
import com.gexiao.lift.service.IRoleAuthService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: gexiao
 * @Date: 2019/4/11 15:42
 * @Description:
 */
@Service
public class RoleAuthServiceImpl extends ServiceImpl<RoleAuthMapper, RoleAuth> implements IRoleAuthService {
    @Override
    public List<RoleAuth> listByRoleId(String roleId) {
        List<RoleAuth> list = list(new LambdaQueryWrapper<RoleAuth>().eq(RoleAuth::getRoleId, roleId));
        return list;
    }
}
