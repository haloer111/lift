package com.gexiao.lift.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gexiao.lift.entity.core.BaseEntity;

/**
 * @Auther: gexiao
 * @Date: 2019/5/21 11:41
 * @Description:
 */
@TableName(value = "contact")
public class Contact extends BaseEntity {

    /**
     * 联系人
     */
    private String name;
    /**
     * 手机号
     */
    private String tel;
    /**
     * 地址
     */
    private String address;

}