package com.gexiao.lift.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gexiao.lift.entity.core.UserAuthBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Auther: gexiao
 * @Date: 2019/4/9 14:36
 * @Description: 用户表
 */
@TableName(value = "sys_authority")
@EqualsAndHashCode(callSuper = true)
@Data
public class Authority extends UserAuthBaseEntity {


    /**
     * 资源路径（支持/*一级匹配、/**多级匹配）
     */
    private String url;
    /**
     * 请求类型httpMethod
     */
    private String httpMethod;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序(数字越小越靠前)
     */
    private int ordered;


}