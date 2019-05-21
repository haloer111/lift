package com.gexiao.lift.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gexiao.lift.dao.UserRoleMapper;
import com.gexiao.lift.entity.UserRole;
import com.gexiao.lift.service.IUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: gexiao
 * @Date: 2019/4/10 15:12
 * @Description:
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {


    @Override
    public List<String> rolesByUserId(String userId) {
        //角色id
        List<String> roleIds = list(new LambdaQueryWrapper<UserRole>()
                                                .eq(UserRole::getUserId, userId))
                                                .stream()
                                                .map(UserRole::getRoleId)
                                                .collect(Collectors.toList());

        return roleIds;
    }
}
