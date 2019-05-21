package com.gexiao.lift.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gexiao.lift.entity.core.UserAuthBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Auther: gexiao
 * @Date: 2019/4/9 14:43
 * @Description: 角色表
 */
@TableName(value = "sys_role")
@EqualsAndHashCode(callSuper = true)
@Data
public class Role extends UserAuthBaseEntity {
    /** 角色 */
    private String name ;
    /** 描述 */
    private String description ;
}
