package com.gexiao.lift.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gexiao.lift.entity.core.UserAuthBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Auther: gexiao
 * @Date: 2019/4/9 14:43
 * @Description: 用户表
 */
@TableName(value = "sys_user")
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends UserAuthBaseEntity {
    /**
     * 用户名称
     */
    private String name;
    /**
     * 别名
     */
    private String alias;
    /**
     * 手机号码
     */
    private String tel;
    /**
     * 登录账号（唯一约束）
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String loginPassword;
}
