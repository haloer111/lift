package com.gexiao.lift.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gexiao.lift.entity.core.UserAuthBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Auther: gexiao
 * @Date: 2019/4/9 14:43
 * @Description: 角色资源表
 */
@TableName(value = "sys_role_auth")
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleAuth extends UserAuthBaseEntity {
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 权限资源id
     */
    private String authId;
}
