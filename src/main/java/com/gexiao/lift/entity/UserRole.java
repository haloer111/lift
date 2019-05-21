package com.gexiao.lift.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gexiao.lift.entity.core.UserAuthBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Auther: Administrator
 * @Date: 2019/4/9 14:43
 * @Description:
 */
@TableName(value = "sys_user_role")
@EqualsAndHashCode(callSuper = true)
@Data
public class UserRole extends UserAuthBaseEntity {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;
}
