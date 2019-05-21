package com.gexiao.lift.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gexiao.lift.entity.core.BaseEntity;
import lombok.Data;

/**
 * @Auther: gexiao
 * @Date: 2019/5/21 11:40
 * @Description:
 */
@TableName(value = "category")
@Data
public class Category extends BaseEntity {

    /**
     * 名称
     */
    private String name;
    /**
     * 父id
     */
    private Integer parentId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    private Integer status;


}
